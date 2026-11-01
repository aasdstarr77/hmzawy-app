import mongoose from 'mongoose';
import {WorkshopTicket,WORKSHOP_STATUSES} from './workshop.model.js';
import {generateReceiptNumber} from '../../utils/generateReceiptNumber.js';

export async function createTicket(req,res,next){
 const session=await mongoose.startSession();
 try{
  let ticket;
  await session.withTransaction(async()=>{
   const receiptNumber=await generateReceiptNumber(new Date(),session);
   [ticket]=await WorkshopTicket.create([{...req.body,receiptNumber,status:'RECEIVED'}],{session});
  });
  res.status(201).json({success:true,data:ticket});
 }catch(error){next(error);}finally{await session.endSession();}
}

export async function trackTicket(req,res,next){
 try{
  const receipt=String(req.body.receiptNumber||'').trim().toUpperCase();
  const phone=String(req.body.customerPhone||'').replace(/[^0-9]/g,'');
  if(!receipt||phone.length<10)return res.status(400).json({success:false,message:'أدخل رقم التذكرة ورقم الهاتف المسجل'});
  const ticket=await WorkshopTicket.findOne({receiptNumber:receipt,customerPhone:{$regex:phone+'$'}})
   .select('receiptNumber deviceType status receivedAt inspectedAt deliveredAt');
  if(!ticket)return res.status(404).json({success:false,message:'بيانات التذكرة غير مطابقة'});
  res.json({success:true,data:ticket});
 }catch(error){next(error);}
}

export async function getTicket(req,res,next){
 try{
  const ticket=await WorkshopTicket.findById(req.params.id);
  if(!ticket)return res.status(404).json({success:false,message:'التذكرة غير موجودة'});
  res.json({success:true,data:ticket});
 }catch(error){next(error);}
}
export async function listTickets(req,res,next){
 try{res.json({success:true,data:await WorkshopTicket.find().sort({receivedAt:-1}).limit(200)});}
 catch(error){next(error);}
}
export async function updateStatus(req,res,next){
 try{
  const {status}=req.body;
  if(!WORKSHOP_STATUSES.includes(status))return res.status(400).json({success:false,message:'حالة غير صحيحة'});
  const ticket=await WorkshopTicket.findByIdAndUpdate(req.params.id,{status,...(status==='DELIVERED'?{deliveredAt:new Date()}:{})},{new:true,runValidators:true});
  if(!ticket)return res.status(404).json({success:false,message:'التذكرة غير موجودة'});
  res.json({success:true,data:ticket});
 }catch(error){next(error);}
}

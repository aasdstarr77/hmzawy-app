import mongoose from 'mongoose';
const schema=new mongoose.Schema({_id:{type:String,required:true},sequence:{type:Number,default:0}},{versionKey:false});const Counter=mongoose.models.Counter||mongoose.model('Counter',schema);
export async function generateReceiptNumber(now=new Date(),session){const year=now.getFullYear();const counter=await Counter.findOneAndUpdate({_id:`workshop-${year}`},{$inc:{sequence:1}},{new:true,upsert:true,setDefaultsOnInsert:true,session});return `HS-${year}-${String(counter.sequence).padStart(3,'0')}`;}

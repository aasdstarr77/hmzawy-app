import jwt from 'jsonwebtoken';import {env} from '../config/env.js';
export function authenticate(req,res,next){const token=req.headers.authorization?.replace(/^Bearer\s+/i,'');if(!token)return res.status(401).json({success:false,message:'سجل الدخول أولاً'});try{req.user=jwt.verify(token,env.jwtSecret);next();}catch{return res.status(401).json({success:false,message:'جلسة الدخول غير صالحة'});}}

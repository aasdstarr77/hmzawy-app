import mongoose from 'mongoose';
export async function connectDatabase(uri){mongoose.set('strictQuery',true);await mongoose.connect(uri,{autoIndex:process.env.NODE_ENV!=='production'});console.info('MongoDB connected');}

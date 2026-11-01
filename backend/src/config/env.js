import 'dotenv/config';
export const env={nodeEnv:process.env.NODE_ENV||'development',port:Number(process.env.PORT||4000),mongoUri:process.env.MONGODB_URI||'mongodb://127.0.0.1:27017/hamzawy_sat',jwtSecret:process.env.JWT_SECRET||'',jwtExpiresIn:process.env.JWT_EXPIRES_IN||'7d',corsOrigin:process.env.CORS_ORIGIN||'*'};
if(env.nodeEnv==='production'&&env.jwtSecret.length<32)throw new Error('JWT_SECRET must contain at least 32 characters in production');

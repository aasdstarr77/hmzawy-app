export function notFound(req,res){res.status(404).json({success:false,message:'المسار غير موجود'});}
export function errorHandler(error,req,res,next){console.error(error);const status=error.statusCode||(error.name==='ValidationError'?400:500);res.status(status).json({success:false,message:status===500?'حصل خطأ في السيرفر':error.message});}

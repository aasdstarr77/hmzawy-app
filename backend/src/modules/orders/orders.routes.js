import {Router} from 'express';import {authenticate} from '../../middlewares/auth.middleware.js';import {createOrder,getOrder,listOrders,updateOrder} from './orders.controller.js';
const router=Router();router.use(authenticate);router.post('/',createOrder);router.get('/',listOrders);router.get('/:id',getOrder);router.patch('/:id',updateOrder);export default router;

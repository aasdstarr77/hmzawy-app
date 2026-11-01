import {Router} from 'express';
import {authenticate} from '../../middlewares/auth.middleware.js';
import {requireAdmin} from '../../middlewares/admin.middleware.js';
import {createTicket,getTicket,listTickets,trackTicket,updateStatus} from './workshop.controller.js';

const router=Router();
// Customer tracking requires both the non-sequential receipt identifier and the phone on the ticket.
router.post('/track',trackTicket);
router.use(authenticate,requireAdmin);
router.get('/',listTickets);
router.post('/',createTicket);
router.get('/:id',getTicket);
router.patch('/:id/status',updateStatus);
export default router;

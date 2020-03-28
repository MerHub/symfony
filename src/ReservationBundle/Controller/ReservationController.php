<?php

namespace ReservationBundle\Controller;

use AppBundle\Entity\chauffeur;
use AppBundle\Entity\user;
use ReservationBundle\Entity\Livraison;
use ReservationBundle\Entity\Reservation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Reservation controller.
 *
 */
class ReservationController extends Controller
{
    /**
     * Lists all reservation entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();
        $user=$this->getUser();
        $id=$user->getId();
        $Puser=$this->getDoctrine()->getRepository(user::class)->find($id);
        $listDriver=$this->getDoctrine()->getRepository(user::class)->findBy(['type'=>'chauffeur']);
        return $this->render('@Reservation/reservation/new.html.twig',[
            'user'=>$Puser,
            'listChauffeur'=>$listDriver
        ]);
    }

    /**
     * Creates a new reservation entity.
     *
     */
    public function newAction(Request $request)
    {
        $user=$this->getUser();
        $id=$user->getId();
        $reservation = new Reservation();
        $livraison = new Livraison();
        $reservationExite=$this->getDoctrine()->getRepository(Reservation::class)->findOneBy(['idClient'=>$id,'typeReservation'=>"reservationSimple"]);
        $Puser=$this->getDoctrine()->getRepository(user::class)->find($id);
        if($reservationExite==null){
            $form = $this->createForm('ReservationBundle\Form\ReservationType', $reservation);
            $form->handleRequest($request);

            if ($form->isSubmitted() && $form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $reservation->setHeure(new \DateTime('now'));
                $em->persist($reservation);
                $em->flush();
                if($reservation->getTypeReservation()=="livraison"){
                    $az=$this->getDoctrine()->getRepository(Reservation::class)->findBy(['typeReservation'=>"livraison"]);
                    $reservations=end($az);
                    $code=$request->get("codeLivraison");
                    return $this->redirectToRoute("livraison_new",["idRservation"=>$reservations->getIdReservation(),"codeLivraison"=>$code]);
                }else{
                    return $this->redirectToRoute("sendMessageChauffeur",array("idReservation"=>$reservation->getIdreservation(),"numClient"=>$Puser->getNTel(),"idDriver"=>$reservation->getIdChauffeur()->getIdUser()->getId()));
                }
            }


            $listDriver=$this->getDoctrine()->getRepository(user::class)->findBy(['type'=>'chauffeur']);
            return $this->render('@Reservation/reservation/new.html.twig',[
                'user'=>$Puser,
                'listChauffeur'=>$listDriver,
                'form' => $form->createView(),
                'formLivraison' => $form->createView()
            ]);
        }else{
            return $this->redirectToRoute('_show', array('idReservation' => $reservationExite->getIdReservation()));
        }

    }

    /**
     * Finds and displays a reservation entity.
     *
     */
    public function showAction(Reservation $reservation)
    {
        $deleteForm = $this->createDeleteForm($reservation);
        $user=$this->getUser();
        $id=$user->getId();
        $Puser=$this->getDoctrine()->getRepository(user::class)->find($id);
        $Chauffeur=$this->getDoctrine()->getRepository(chauffeur::class)->find($reservation->getIdChauffeur());
        return $this->render('@Reservation/reservation/show.html.twig', array(
            'user'=>$Puser,
            'reservation' => $reservation,
            'delete_form' => $deleteForm->createView(),
            'chauffeur' => $Chauffeur,
        ));
    }

    /**
     * Displays a form to edit an existing reservation entity.
     *
     */
    public function editAction(Request $request, Reservation $reservation)
    {
        $deleteForm = $this->createDeleteForm($reservation);
        $editForm = $this->createForm('ReservationBundle\Form\ReservationType', $reservation);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reservation_homepage', array('idReservation' => $reservation->getIdreservation()));
        }
        $user=$this->getUser();
        $id=$user->getId();
        $Puser=$this->getDoctrine()->getRepository(user::class)->find($id);
        $listDriver=array();
        return $this->render('@Reservation/reservation/new.html.twig', array(
            'user'=> $Puser,
            'reservation' => $reservation,
            'form' => $editForm->createView(),
            'listChauffeur'=>$listDriver,
        ));
    }

    /**
     * Deletes a reservation entity.
     *
     */
    public function deleteAction(Request $request, Reservation $reservation)
    {
        $form = $this->createDeleteForm($reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($reservation);
            $em->flush();
        }

        return $this->redirectToRoute('reservation_homepage');
    }

    /**
     * Creates a form to delete a reservation entity.
     *
     * @param Reservation $reservation The reservation entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Reservation $reservation)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('_delete', array('idReservation' => $reservation->getIdreservation())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}

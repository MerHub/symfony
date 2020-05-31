<?php

namespace ReservationBundle\Controller;

use AppBundle\Entity\Notification;
use ReservationBundle\Entity\Livraison;
use ReservationBundle\Entity\Reservation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Livraison controller.
 *
 */
class LivraisonController extends Controller
{
    /**
     * Lists all livraison entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();
        $user=$this->getUser();
        $id=$user->getId();
        $livraisons=[];
        $livraisons=$this->getDoctrine()->getRepository(Livraison::class)->ListeLivraisonDetaille($id);
        return $this->render('@Reservation/livraison/index.html.twig', array(
            'livraisons' => $livraisons,
        ));
    }

    /**
     * Creates a new livraison entity.
     *
     */
    public function newAction($idRservation,$codeLivraison="vide")
    {
        $idRservation=(int) $idRservation;
        $livraison = new Livraison();
        $reservation = $this->getDoctrine()->getRepository(Reservation::class)->find($idRservation);
        $livraison->setIdReservation($reservation);
        $livraison->setCodeLivraison($codeLivraison);
        $livraison->setEtat(0);
        $em = $this->getDoctrine()->getManager();
        $em->persist($livraison);


        $titre = "Nouvel livraison";
        $body = "vous avez une livraison à effectuer Connectez vous";
        $operation="add";
        $notification = new Notification();
        $notification
            ->setTitle($titre)
            ->setDescription($body)
            ->setIdSend($livraison->getIdReservation()->getIdClient()->getIdUser())
            ->setIdReceive($livraison->getIdReservation()->getIdChauffeur()->getIdUser())
            ->setIcon($operation)
            ->setRoute('avis_show')
            ->setParameters(['idReceive'=>$livraison->getIdReservation()->getIdChauffeur()->getIdUser()->getId()])
        ;
        $pusher = $this->get('mrad.pusher.notificaitons');
        $pusher->trigger($notification);
        $em->persist($notification);
        $em->flush();


        return $this->redirectToRoute('livraison_index');
    }

    /**
     * Finds and displays a livraison entity.
     *
     */
    public function showAction(Livraison $livraison)
    {
        $deleteForm = $this->createDeleteForm($livraison);

        return $this->render('@Reservation/livraison/show.html.twig', array(
            'livraison' => $livraison,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    public function serviceAddLivraisonAction($codeLivraison,$idChauffeur,$idClient,$depart,$arrive,$prix,$typeReservation,$latitude,$longitude,$latitude2,$longitude2){

        $reservation=new Reservation();
        $chaffeur=$this->getDoctrine()->getRepository(chauffeur::class)->find($idChauffeur);
        $client=$this->getDoctrine()->getRepository(Client::class)->find($idClient);
        $reservation->setIdChauffeur($chaffeur);
        $reservation->setIdClient($client);
        $reservation->setDepart($depart);
        $reservation->setArrive($arrive);
        $reservation->setPrix($prix);
        $reservation->setTypeReservation($typeReservation);
        $reservation->setLatitude($latitude);
        $reservation->setLongitude($longitude);
        $reservation->setLatitude2($latitude2);
        $reservation->setLongitude2($longitude2);
        $em=$this->getDoctrine()->getManager();
        $em->persist($reservation);
        $em->flush();
        $reservation=$this->getDoctrine()->getRepository(Reservation::class)->findOneBy(["latitude"=>$latitude,"longitude"=>$longitude,"latitude2"=>$latitude2,"longitude2"=>$longitude2]);
        $livraison=new Livraison();
        $livraison->getIdReservation($reservation);
        $livraison->setEtat(0);
        $livraison->setCodeLivraison($codeLivraison);
        $em->persist($livraison);
        $em->flush();

        header('Content-type: application/json');
        return  new Response(json_encode( ["requette"=>["reponse"=>"oui"]] ));

    }

    /**
     * Displays a form to edit an existing livraison entity.
     *
     */
    public function editAction(Request $request, Livraison $livraison)
    {
        $deleteForm = $this->createDeleteForm($livraison);
        $editForm = $this->createForm('ReservationBundle\Form\LivraisonType', $livraison);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('livraison_edit', array('id' => $livraison->getId()));
        }

        return $this->render('@Reservation/livraison/edit.html.twig', array(
            'livraison' => $livraison,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a livraison entity.
     *
     */
    public function deleteAction(Livraison $livraison)
    {
        $em = $this->getDoctrine()->getManager();
        $em->remove($livraison);
        $em->flush();

        return $this->redirectToRoute('livraison_index');
    }

    /**
     * Creates a form to delete a livraison entity.
     *
     * @param Livraison $livraison The livraison entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Livraison $livraison)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('livraison_delete', array('id' => $livraison->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}

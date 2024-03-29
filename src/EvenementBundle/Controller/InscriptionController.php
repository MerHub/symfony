<?php

namespace EvenementBundle\Controller;

use AppBundle\Entity\user;
use EvenementBundle\Entity\Inscription;
use EvenementBundle\Entity\Event;
use http\Client;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

/**
 * Inscription controller.
 *
 */
class InscriptionController extends Controller
{
    /**
     * Lists all inscription entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $inscriptions = $em->getRepository('EvenementBundle:Inscription')->findAll();

        return $this->render('@Evenement/inscription/index.html.twig', array(
            'inscriptions' => $inscriptions,
        ));
    }

    /**
     * Creates a new inscription entity.
     *
     */
    public function newAction(Request $request)
    {
        $inscription = new Inscription();
        $user=$this->getUser();
        $id=$user->getId();
        $Puser=$this->getDoctrine()->getRepository(\AppBundle\Entity\Client::class)->find($id);
        $form = $this->createForm('EvenementBundle\Form\InscriptionType', $inscription);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $inscription->setIdClient($Puser);
            $em = $this->getDoctrine()->getManager();
            $em->persist($inscription);
            $em->flush();

            return $this->redirectToRoute('inscription_new');
        }

        return $this->render('@Evenement/inscription/new.html.twig', array(
            'user'=>$Puser,
            'inscription' => $inscription,
            'form' => $form->createView(),
        ));
    }
    public function newMAction(Event $idEvent,user $idClient,int $nn)
    {
        $i = new Inscription();
        $nbr=$idEvent->getNbrPlace();
        $nbr=$nbr-$nn;
        $idEvent->setNbrPlace($nbr);
        $velo_s = $this->getDoctrine()->getRepository(Event::class)->find($idEvent);
        $userr = $this->getDoctrine()->getRepository(\AppBundle\Entity\Client::class)->find($idClient);
        $em = $this->getDoctrine()->getManager();

        $i->setIdEvent($velo_s);
  //      $user=$this->getUser();
//        $id=$user->getId();
        $i->setIdClient($userr);
        $em->persist($i);
        $em->flush();
        header('Content-type: application/json');
        return  new Response(json_encode( ["reponse"=>"oui"] ));
    }
    /**
     * Finds and displays a inscription entity.
     *
     */
    public function showAction(Inscription $inscription)
    {
        $deleteForm = $this->createDeleteForm($inscription);

        return $this->render('@Evenement/inscription/show.html.twig', array(
            'inscription' => $inscription,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing inscription entity.
     *
     */
    public function editAction(Request $request, Inscription $inscription)
    {
        $deleteForm = $this->createDeleteForm($inscription);
        $editForm = $this->createForm('EvenementBundle\Form\InscriptionType', $inscription);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('inscription_edit', array('id' => $inscription->getId()));
        }

        return $this->render('@Evenement/inscription/edit.html.twig', array(
            'inscription' => $inscription,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a inscription entity.
     *
     */
    public function deleteAction(Request $request, Inscription $inscription)
    {
        $form = $this->createDeleteForm($inscription);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($inscription);
            $em->flush();
        }

        return $this->redirectToRoute('inscription_index');
    }

    /**
     * Creates a form to delete a inscription entity.
     *
     * @param Inscription $inscription The inscription entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Inscription $inscription)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('inscription_delete', array('id' => $inscription->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }


    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('qi');
        $rec = $em->getRepository('EvenementBundle:Inscription')->SearchiOffre($libelle);

        if (!$rec) {
            $result['offre']['error'] = "There is not an event for this name ";
        } else {
            $result['offre'] = $rec;
        }
        return new Response(json_encode($result));
    }
//$this->getRealEntities($rec);
    public function getRealEntities( $rec)
    {
        foreach ($rec as $r) {
            $realEntities[$r->getId()]=[$r->getIdEvent(),$r->getIdEvent()->getNom(),$r->getIdClient()];
        }
        return $realEntities;
    }







}

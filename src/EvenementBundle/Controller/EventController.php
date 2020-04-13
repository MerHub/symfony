<?php

namespace EvenementBundle\Controller;

use EvenementBundle\Entity\Event;
use EvenementBundle\Entity\Inscription;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Event controller.
 *
 */
class EventController extends Controller
{
    /**
     * Lists all event entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $events = $em->getRepository('EvenementBundle:Event')->findAll();

        return $this->render('@Evenement/event/index.html.twig', array(
            'events' => $events,
        ));
    }
    public function frontAction()
    {
        $em = $this->getDoctrine()->getManager();

        $events = $em->getRepository('EvenementBundle:Event')->findAll();

        return $this->render('@Evenement/event/eventlistFront.html.twig', array(
            'events' => $events,
        ));
    }

    /**
     * Creates a new event entity.
     *
     */
    public function newAction(Request $request)
    {
        $event = new Event();
        $form = $this->createForm('EvenementBundle\Form\EventType', $event);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($event);
            $em->flush();

            return $this->redirectToRoute('event_show', array('idEvent' => $event->getIdevent()));
        }

        return $this->render('@Evenement/event/new.html.twig', array(
            'event' => $event,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a event entity.
     *
     */
    public function showAction(Event $event)
    {
        $deleteForm = $this->createDeleteForm($event);

        return $this->render('@Evenement/event/show.html.twig', array(
            'event' => $event,
            'delete_form' => $deleteForm->createView(),
        ));
    }



    public function addAction(Event $id2)
    {
        if ($id2->getNbrPlace()!=0)
        { $i = new Inscription();
        $nbr=$id2->getNbrPlace();
        $nbr--;
        $id2->setNbrPlace($nbr);
        $user=$this->getUser();
        $id=$user->getId();
        $i->setIdEvent($id2);

        $Puser=$this->getDoctrine()->getRepository(\AppBundle\Entity\Client::class)->find($id);

        $i->setIdClient($Puser);

        $em = $this->getDoctrine()->getManager();
        $em->persist($i);
        $em->flush();
            $this->addFlash(
                'notice',
                'Your changes were saved!'
            );
        return $this->redirectToRoute('event_front');

    }
    else{$this->addFlash('success', 'Article Created! Knowledge is power!');}
    }

    


    /**
     * Displays a form to edit an existing event entity.
     *
     */
    public function editAction(Request $request, Event $event)
    {
        $deleteForm = $this->createDeleteForm($event);
        $editForm = $this->createForm('EvenementBundle\Form\EventType', $event);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('event_index', array('idEvent' => $event->getIdevent()));
        }

        return $this->render('@Evenement/event/edit.html.twig', array(
            'event' => $event,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a event entity.
     *
     */
    public function deleteAction(Request $request, Event $event)
    {
        $form = $this->createDeleteForm($event);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($event);
            $em->flush();
        }

        return $this->redirectToRoute('event_index');
    }

    /**
     * Creates a form to delete a event entity.
     *
     * @param Event $event The event entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Event $event)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('event_delete', array('idEvent' => $event->getIdevent())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}

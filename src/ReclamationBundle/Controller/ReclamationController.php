<?php

namespace ReclamationBundle\Controller;


use DateTime;

use ReclamationBundle\Entity\reclamation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Constraints\Date;
use ReclamationBundle\Entity\typeReclamation;
use AppBundle\Entity\user;


/**
 * Reclamation controller.
 *
 */
class ReclamationController extends Controller
{
    /**
     * Lists all reclamation entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $reclamations = $em->getRepository('ReclamationBundle:reclamation')->findAll();

        return $this->render('@Reclamation/reclamation/index.html.twig', array(
            'reclamations' => $reclamations,
        ));
    }

    /**
     * Creates a new reclamation entity.
     *
     */
    public function newAction(Request $request)
    {
        $reclamation = new reclamation();
        $form = $this->createForm('ReclamationBundle\Form\ReclamationType', $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($reclamation);
            $em->flush();

            return $this->redirectToRoute('reclamation_show', array('idRec' => $reclamation->getIdrec()));
        }

        return $this->render('@Reclamation/reclamation/new.html.twig', array(
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ));
    }
    public function newMAction( $idClient,  $idchauffeur, $typeReclamation)
    {
        $reclamation = new reclamation();



            $em = $this->getDoctrine()->getManager();

    $reclamation->setEtat(1);
            $date=new DateTime('now+1 hours');
            $reclamation->setDateAjout($date);
        $objjj = $this->getDoctrine()
            ->getRepository(user::class)
            ->find($idchauffeur);
        $reclamation->setIdchauffeur($objjj);


        $objj = $this->getDoctrine()
            ->getRepository(user::class)
            ->find($idClient);
             $reclamation->setIdClient($objj);

            $obj = $this->getDoctrine()
                ->getRepository(typeReclamation::class)
                ->find($typeReclamation);

            $reclamation->setTypeReclamation($obj);

        $em->persist($reclamation);
            $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reclamation);
        return new JsonResponse($formatted);


    }

    /**
     * Finds and displays a reclamation entity.
     *
     */
    public function showAction(Reclamation $reclamation)
    {
        $deleteForm = $this->createDeleteForm($reclamation);

        return $this->render('@Reclamation/reclamation/show.html.twig', array(
            'reclamation' => $reclamation,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing reclamation entity.
     *
     */
    public function editAction(Request $request, Reclamation $reclamation)
    {
        $deleteForm = $this->createDeleteForm($reclamation);
        $editForm = $this->createForm('ReclamationBundle\Form\ReclamationType', $reclamation);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reclamation_edit', array('idRec' => $reclamation->getIdrec()));
        }

        return $this->render('@Reclamation/reclamation/edit.html.twig', array(
            'reclamation' => $reclamation,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a reclamation entity.
     *
     */
    public function deleteAction(Request $request, Reclamation $reclamation)
    {
        $form = $this->createDeleteForm($reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($reclamation);
            $em->flush();
        }

        return $this->redirectToRoute('reclamation_index');
    }

    /**
     * Creates a form to delete a reclamation entity.
     *
     * @param Reclamation $reclamation The reclamation entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Reclamation $reclamation)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('reclamation_delete', array('idRec' => $reclamation->getIdrec())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }
    public function showReclamationAction($id)
    {
        $idc=$id;
        $list=$this->getDoctrine()->getRepository(reclamation::class)->findBy(['idChauffeur'=>$idc]);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($list);
        return new JsonResponse($formatted);
    }
    public function getTypesAction()
    {
        $list=$this->getDoctrine()->getRepository(typeReclamation::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($list);
        return new JsonResponse($formatted);
    }
}

<?php

namespace OffreBundle\Controller;

use Symfony\Component\HttpFoundation\Response;
use OffreBundle\Entity\Offre;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;


/**
 * Offre controller.
 *
 */
class OffreController extends Controller
{
    /**
     * Lists all offre entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $offres = $em->getRepository('OffreBundle:Offre')->findAll();

        return $this->render('@Offre/offre/index.html.twig', array(
            'offres' => $offres,
        ));
    }


    public function listAction()
    {
        $em = $this->getDoctrine()->getManager();

        $offres = $em->getRepository('OffreBundle:Offre')->findAll();

        return $this->render('@Offre/offre/list.html.twig', array(
            'offres' => $offres,
        ));
    }


    /**
     * Creates a new offre entity.
     *
     */
    public function newAction(Request $request)
    {
        $offre = new Offre();
        $form = $this->createForm('OffreBundle\Form\OffreType', $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $file=$offre->getImg();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $offre->setImg($fileName);
            $em->persist($offre);
            $em->flush();

            return $this->redirectToRoute('offre_show', array('idOffre' => $offre->getIdoffre()));
        }

        return $this->render('@Offre/offre/new.html.twig', array(
            'offre' => $offre,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a offre entity.
     *
     */
    public function showAction(Offre $offre)
    {
        $deleteForm = $this->createDeleteForm($offre);

        return $this->render('@Offre/offre/show.html.twig', array(
            'offre' => $offre,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing offre entity.
     *
     */
    public function editAction(Request $request, Offre $offre)
    {
        $deleteForm = $this->createDeleteForm($offre);
        $editForm = $this->createForm('OffreBundle\Form\OffreType', $offre);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
           $em= $this->getDoctrine()->getManager();
            $file=$offre->getImg();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $offre->setImg($fileName);
            $em->flush();

            return $this->redirectToRoute('offre_show', array('idOffre' => $offre->getIdoffre()));
        }

        return $this->render('@Offre/offre/edit.html.twig', array(
            'offre' => $offre,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a offre entity.
     *
     */
    public function deleteAction(Request $request, Offre $offre)
    {
        $form = $this->createDeleteForm($offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($offre);
            $em->flush();
        }

        return $this->redirectToRoute('offre_list');
    }

    /**
     * Creates a form to delete a offre entity.
     *
     * @param Offre $offre The offre entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Offre $offre)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('offre_delete', array('idOffre' => $offre->getIdoffre())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
    public function searchhAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('q');
        $rec = $em->getRepository('OffreBundle:Offre')->SearchOffre($libelle);

        if (!$rec) {
            $result['offre']['error'] = "Offer does not exist :( ";
        } else {
            $result['offre'] = $this->getRealEntities($rec);
        }
        return new Response(json_encode($result));
    }

    public function getRealEntities($rec)
    {
        foreach ($rec as $rec) {
            $realEntities[$rec->getIdoffre()] = [$rec->getDateD(), $rec->getDateF(), $rec->getType() ,  $rec->getNom(),$rec->getReductionOffre(),$rec->getCodePromo(),$rec->getImg()];

        }
        return $realEntities;
    }
}

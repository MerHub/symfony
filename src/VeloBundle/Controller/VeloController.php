<?php

namespace VeloBundle\Controller;

use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Response;
use VeloBundle\Entity\Velo;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Velo controller.
 *
 */
class VeloController extends Controller
{
    /**
     * Lists all velo entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $velos = $em->getRepository('VeloBundle:Velo')->findAll();

        return $this->render('@Velo/velo/index.html.twig', array(
            'velos' => $velos,
        ));
    }

    public function serviceListAction(){
        $list=$this->getDoctrine()->getRepository(Velo::class)->findAll();
        $data=["liste"=>[]];
        forEach($list as $key=>$value){
            array_push($data["liste"],[
                "id"=>$value->getId(),
                "type"=>$value->getType(),
                "adresse"=>$value->getAdresse(),
                "photo"=>$value->getPhoto(),
                "qte"=>$value->getQte(),
                "prix"=>$value->getPrix(),
                "latitude"=>$value->getLatitude(),
                "longitude"=>$value->getLongitude(),
            ]);
        }
        header('Content-type: application/json');
        return  new Response(json_encode( $data ));
    }

    /**
     * Creates a new velo entity.
     *
     */
    public function newAction(Request $request)
    {
        $velo = new Velo();
        $form = $this->createForm('VeloBundle\Form\VeloType', $velo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $file=$velo->getPhoto();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $velo->setPhoto($fileName);
            $em->persist($velo);
            $em->flush();

            return $this->redirectToRoute('velo_index', array('id' => $velo->getId()));
        }

        return $this->render('@Velo/velo/new.html.twig', array(
            'velo' => $velo,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a velo entity.
     *
     */
    public function showAction(Velo $velo)
    {
        $deleteForm = $this->createDeleteForm($velo);

        return $this->render('@Velo/velo/show.html.twig', array(
            'velo' => $velo,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing velo entity.
     *
     */
    public function editAction(Request $request, Velo $velo)
    {
        $deleteForm = $this->createDeleteForm($velo);
        $editForm = $this->createForm('VeloBundle\Form\VeloType', $velo);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
           $em= $this->getDoctrine()->getManager();
            /** @var UploadedFile $file */
            $file=$velo->getPhoto();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $velo->setPhoto($fileName);
           $em->flush();

            return $this->redirectToRoute('velo_show', array('id' => $velo->getId()));
        }

        return $this->render('@Velo/velo/edit.html.twig', array(
            'velo' => $velo,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a velo entity.
     *
     */
    public function deleteAction(Request $request, Velo $velo)
    {
        $form = $this->createDeleteForm($velo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($velo);
            $em->flush();
        }

        return $this->redirectToRoute('velo_index');
    }

    /**
     * Creates a form to delete a velo entity.
     *
     * @param Velo $velo The velo entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Velo $velo)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('velo_delete', array('id' => $velo->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('q');
        $rec = $em->getRepository('VeloBundle:Velo')->SearchVelo($libelle);

        if (!$rec) {
            $result['velo']['error'] = "Bicycle does not exist :( ";
        } else {
            $result['velo'] = $this->getRealEntities($rec);
        }
        return new Response(json_encode($result));
    }

    public function getRealEntities($rec)
    {
        foreach ($rec as $rec) {
            $realEntities[$rec->getId()] = [$rec->getType(), $rec->getAdresse(), $rec->getQte() ,  $rec->getPhoto(),$rec->getPrix()];

        }
        return $realEntities;
    }
    public function excelAction()
    {
        $em = $this->getDoctrine()->getManager();
        $velos = $em->getRepository('VeloBundle:Velo')->findAll();
        $writer = $this->container->get('egyg33k.csv.writer');
        $csv = $writer::createFromFileObject(new \SplTempFileObject());
        $csv->insertOne(['type', 'adresse', 'qte','prix']);
        foreach ($velos as $velo){
            $csv->insertOne([$velo->getType(),$velo->getAdresse(),$velo->getQte(),$velo->getPrix()]);
        }
        $csv->output('velo.csv');
        die('excel');
    }
}

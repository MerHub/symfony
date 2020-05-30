<?php

namespace TaxiBundle\Controller;
use AppBundle\Entity\user;
use AppBundle\Entity\chauffeur;
use Dompdf\Dompdf;
use Dompdf\Options;
use TaxiBundle\Entity\Taxi;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Taxi controller.
 *
 */
class TaxiController extends Controller
{
    /**
     * Lists all taxi entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $taxis = $em->getRepository('TaxiBundle:Taxi')->findAll();

        return $this->render('taxi/index.html.twig', array(
            'taxis' => $taxis,
        ));
    }

    /**
     * Creates a new taxi entity.
     *
     */
    public function newAction(Request $request)
    {
        $user=$this->getUser();
        $taxi=$this->getDoctrine()->getRepository(Taxi::class)->findOneBy(["idChauffeur"=>$user->getId()]);
        if($taxi!=null){
            return $this->redirectToRoute("taxi_edit",["idTaxi"=>$taxi->getIdTaxi()]);
        }
        $taxi = new Taxi();
        $form = $this->createForm('TaxiBundle\Form\TaxiType', $taxi);
        $form->handleRequest($request);

       $chauffeur= $this->getDoctrine()->getRepository(chauffeur::class)->find($user->getId());

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $file=$taxi->getPhoto();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $taxi->setPhoto($fileName);
            $taxi->setIdChauffeur($chauffeur);
            $em->persist($taxi);
            $em->flush();

            return $this->redirectToRoute('taxi_show', array('idTaxi' => $taxi->getIdtaxi()));
        }

        return $this->render('taxi/new.html.twig', array(
            'taxi' => $taxi,
            'form' => $form->createView(),
            'user'=>$user
        ));
    }

    /**
     * Finds and displays a taxi entity.
     *
     */
    public function showAction(Taxi $taxi)
    {
        $deleteForm = $this->createDeleteForm($taxi);

        return $this->render('taxi/show.html.twig', array(
            'taxi' => $taxi,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing taxi entity.
     *
     */
    public function editAction(Request $request, Taxi $taxi)
    {
        $deleteForm = $this->createDeleteForm($taxi);
        $editForm = $this->createForm('TaxiBundle\Form\TaxiType', $taxi);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $em=$this->getDoctrine()->getManager();
            $file=$taxi->getPhoto();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $taxi->setPhoto($fileName);
            $em->flush();

            return $this->redirectToRoute('taxi_edit', array('idTaxi' => $taxi->getIdtaxi()));
        }

        return $this->render('taxi/edit.html.twig', array(
            'taxi' => $taxi,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a taxi entity.
     *
     */
    public function deleteAction(Request $request, Taxi $taxi)
    {
        $form = $this->createDeleteForm($taxi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($taxi);
            $em->flush();
        }

        return $this->redirectToRoute('taxi_index');
    }

    /**
     * Creates a form to delete a taxi entity.
     *
     * @param Taxi $taxi The taxi entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Taxi $taxi)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('taxi_delete', array('idTaxi' => $taxi->getIdtaxi())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
    public function pdfAction($idTaxi)
    {
        $t = new Taxi();
        $Taxi =$this->getDoctrine()->getRepository(Taxi::class)->find($idTaxi);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        $pdfOptions->set('isRemoteEnabled', true);
        $photo =$Taxi->getPhoto();
        $numChassi=$Taxi->getNumChassis();
        //$userName=getIdChauffeur();
        $user =$this->getDoctrine()->getRepository(user::class)->find($Taxi->getIdChauffeur());
        $name = $user->getUsername();
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);


        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('taxi/pdf.html.twig', [
            'photo' => $photo,'numChassi' => $numChassi,'username' => $name
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();


        $dompdf->stream($idTaxi.$name.".pdf", [
            "Attachment" => false
        ]);
    }
}

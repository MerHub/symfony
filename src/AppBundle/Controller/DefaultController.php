<?php

namespace AppBundle\Controller;

use AppBundle\Entity\chauffeur;
use AppBundle\Entity\Client;
use AppBundle\Entity\Notification;
use AppBundle\Entity\user;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpClient\HttpClient;
use TaxiBundle\Entity\Taxi;

// version ghada
class DefaultController extends Controller
{
    /**
     * @Route("/Map", name="serviceMap")
     */
    public function serviceMap()
    {
        return $this->render('templateFront/map.html.twig');
    }

    /**
     * @Route("/ServiceAddUser/{email}/{username}/{password}/{type}", name="serviceAdd")
     */
    public function ServiceAddUser($email,$username,$password,$type){
        $userManager = $this->get('fos_user.user_manager');

        // Or you can use the doctrine entity manager if you want instead the fosuser manager
        // to find
        //$em = $this->getDoctrine()->getManager();
        //$usersRepository = $em->getRepository("mybundleuserBundle:User");
        // or use directly the namespace and the name of the class
        // $usersRepository = $em->getRepository("mybundle\userBundle\Entity\User");
        //$email_exist = $usersRepository->findOneBy(array('email' => $email));

        $email_exist = $userManager->findUserByEmail($email);
        $username_exist = $userManager->findUserByUsername($username);

        // Check if the user exists to prevent Integrity constraint violation error in the insertion
        $data="oui";
        if($email_exist){
            $data = [ "requette"=>['reponse'=>'no','message'=>"cette email existe deja"]];
        }
        if($username_exist){
            $data = [ "requette"=>['reponse'=>'no','message'=>"ce username existe deja"]];
        }
        if(!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $data = [ "requette"=>['reponse'=>'no','message'=>"email incorrect"]];
        }

        if($data=="oui"){
            $user = $userManager->createUser();
   $user->setUsername($username);
   $user->setEmail($email);
   $user->setEmailCanonical($email);
   $user->setType($type);
   //$user->setLocked(0); // don't lock the user
   $user->setEnabled(1); // enable the user or enable it later with a confirmation token in the email
   // this method will encrypt the password with the default settings :)
   $user->setPlainPassword($password);
   $userManager->updateUser($user);
            $em=$this->getDoctrine()->getManager();
            $data = [ "requette"=>['reponse'=>'yes']];
            if($type=="client"){
                $client=new Client();
                $client->setIdUser($user);

                $em->persist($client);
                $em->flush();
            }
            if($type=="chauffeur"){
                $chauffeur=new chauffeur();
                $chauffeur->setPhoto("");
                $chauffeur->setIdUser($user);
                $taxi=new Taxi();
                $taxi->setPhoto("");
                $taxi->setNumChassis("");
                $taxi->setIdChauffeur($chauffeur);
                $em->persist($chauffeur);
                $em->persist($taxi);
                $em->flush();
            }
        }

        header('Content-type: application/json');
        return  new Response(json_encode( $data ));

    }



    /**
     * @Route("/listChaffeur/maList", name="listChaffeur")
     */
    public function listChaffeur()
    {
        $array=["liste"=>[]];
        $data=$this->getDoctrine()->getRepository(chauffeur::class)->findAll();
        foreach ($data as $key=>$value){
            array_push($array["liste"],[
                "id"=>$value->getIdUser()->getId(),
                "adresse"=>$value->getAdresse(),
                "cin"=>$value->getCin(),
                "permis"=>$value->getPermis(),
                "nom"=>$value->getNom(),
                "prenom"=>$value->getPrenom(),
                "photo"=>$value->getPhoto(),
                "numero"=>$value->getIdUser()->getNTel(),
                "type"=>$value->getIdUser()->getType(),
                "mail"=>$value->getIdUser()->getEmail(),
                "latitude"=>$value->getIdUser()->getLatitude(),
                "longitude"=>$value->getIdUser()->getLongitude(),
                "username"=>$value->getIdUser()->getUsername(),
            ]);
        }
        header('Content-type: application/json');
        return  new Response(json_encode( $array ));
    }

    /**
     * @Route("/serviceAdreeseGPS/{lat}/{lon}", name="ServiceAdreeseGPS")
     */
    public function ServiceAdreeseGPS($lat,$lon)
    {
        $client = HttpClient::create();
        $response = $client->request('GET', "https://nominatim.openstreetmap.org/reverse?format=json&lat=".$lat."&lon=".$lon);

        $statusCode = $response->getStatusCode();
// $statusCode = 200
        $contentType = $response->getHeaders()['content-type'][0];
// $contentType = 'application/json'
        $content = $response->getContent();
// $content = '{"id":521583, "name":"symfony-docs", ...}'
        $content = $response->toArray();
        $data=["adresse"=>["adr"=>$content["display_name"]]];
        header('Content-type: application/json');
        return  new Response(json_encode( $data ));
    }



    /**
     * @Route("/serviceGetDirection/{lat1}/{lon1}/{lat2}/{lon2}", name="serviceGetDirection")
     */
    public function serviceGetDirection($lat1,$lon1,$lat2,$lon2)
    {
    $url="https://maps.googleapis.com/maps/api/directions/json?origin=".$lat1.",".$lon1."&destination=".$lat2.",".$lon2."&key=AIzaSyD2Ws0KYSjxNXXgRh8jRBGZgrXqgNHzWbI";
        $client = HttpClient::create();
        $response = $client->request('GET',$url);
        $content = json_decode($response->getContent());
        $data=["routes"=>[]];
        forEach($content->routes[0]->legs[0]->steps as $key=>$value){
            $html_instructions=$value->html_instructions;
            $html_instructions=str_replace("<b>","",$html_instructions);
            $html_instructions=str_replace("b>","",$html_instructions);
            $html_instructions=str_replace("</","",$html_instructions);
            $html_instructions=str_replace("<div","",$html_instructions);
            $html_instructions=str_replace("style=\"font-size:0.9em","",$html_instructions);
            $html_instructions=str_replace('">',"",$html_instructions);
            $html_instructions=str_replace('div>',"",$html_instructions);
            $value->html_instructions=$html_instructions;
            array_push($data["routes"],$value);
    }

        return  new Response(json_encode($data));
    }



    /**
     * @Route("/serviceCurrentDate/", name="serviceCurrentDate")
     */
    public function serviceCurrentDate()
    {
        $data=["date"=>[
            "annee"=>date("Y"),
            "mois"=>date("M"),
            "jour"=>date("d"),
            "heures"=>date("H")+1,
            "minutes"=>date("i")
        ]];
        header('Content-type: application/json');
        return  new Response(json_encode( $data ));
    }

    /**
     * @Route("/servicesetUsers/{username}/{email}/{password}/{numero}/{id}", name="servicesetUsers")
     */
    public function servicesetUsers($username,$email,$password,$numero,$id)
    {
        $user=$this->getDoctrine()->getRepository(user::class)->find($id);
        $mail=$this->getDoctrine()->getRepository(user::class)->findBy(["email"=>$email]);
        $num=$this->getDoctrine()->getRepository(user::class)->findBy(["nTel"=>$numero]);
        $usernam=$this->getDoctrine()->getRepository(user::class)->findBy(["username"=>$username]);
        $data=["requette"=>["reponse"=>"oui"]];
        if(sizeof($mail)!=0 && $mail[0]->getEmail()!=$user->getEmail()){
            $data=["requette"=>["reponse"=>"no","message"=>"ce email existe deja"]];
        }
        if(sizeof($num)!=0 && $num[0]->getNTel()!=$user->getNTel()){
            $data=["requette"=>["reponse"=>"no","message"=>"ce numero existe deja"]];
        }
        if(sizeof($usernam)!=0 && $usernam[0]->getUsername()!=$user->getUsername()){
            $data=["requette"=>["reponse"=>"no","message"=>"ce username existe deja"]];
        }

        if($data["requette"]["reponse"]=="oui"){
            $encoder_service = $this->get('security.encoder_factory');
            $encoder = $encoder_service->getEncoder($user);
            if ($encoder->isPasswordValid($user->getPassword(), $password, $user->getSalt())) {
                $user->setEmail($email);
                $user->setUsername($username);
                $user->setNTel($numero);
                $em=$this->getDoctrine()->getManager();
                $em->persist($user);
                $em->flush();
            }else{
                $data=["requette"=>["reponse"=>"no","message"=>"password incorrect"]];
            }
        }

        header('Content-type: application/json');
        return  new Response(json_encode( $data ));
    }







    /**
     * @Route("/serviceLogin/{username}/{password}", name="serviceLogin")
     */
    public function serviceLogin($username,$password)
    {
        $user=$this->getDoctrine()->getRepository(user::class)->findOneBy(["username"=>$username]);
        if($user==null){
            $data = [ "requette"=>['reponse'=>'no']];
        }else{
            $encoder_service = $this->get('security.encoder_factory');
            $encoder = $encoder_service->getEncoder($user);
            $data = [ "requette"=>['reponse'=>'no']];
            if($encoder->isPasswordValid($user->getPassword(),$password,$user->getSalt())){
                $data = [ "requette"=>['reponse'=>'yes','user'=>['longitude'=>$user->getLongitude(),'latitude'=>$user->getLatitude(),'numero'=>$user->getNTel(),'username' =>$user->getUsername(),'email' =>$user->getEmail(),'id_user'=>$user->getId(),'type'=>$user->getType()]]];
            }
        }



        $pass=password_hash($password, PASSWORD_DEFAULT);

        header('Content-type: application/json');
        return  new Response(json_encode( $data ));
    }
    /**
     * @Route("/markAll", name="markAllRead")
     */
    public function mark()
    {
        $user = $this->getUser();
        $this->getDoctrine()->getRepository(Notification::class)->AllMark($user->getId());
        return new Response("ok");
    }

    /**
     * @Route("/message/{idReservation}/{numClient}/{idDriver}", name="sendMessageChauffeur")
     */
    public function sendMessageChauffeur($idReservation,$numClient,$idDriver)
    {
        //returns an instance of Vresh\TwilioBundle\Service\TwilioWrapper
        $twilio = $this->get('twilio.api');
        $numChauffeur=$this->getDoctrine()->getRepository(user::class)->find($idDriver)->getNTel();
        $message = $twilio->account->messages->sendMessage(
            '+12018014274', // From a Twilio number in your account
            '+216'.$numChauffeur, // Text any number
            "Connect you, you have an reservation : phone number : +216 ".$numClient
        );

        //get an instance of \Service_Twilio
        //$otherInstance = $twilio->createInstance('BBBB', 'CCCCC');

        return $this->redirectToRoute('_show', array('idReservation' => $idReservation));
    }
    /**
     * @Route("/login", name="loginpage")
     */
    public function loginAction(Request $request)
    {
        // replace this example code with whatever you need
        return $this->redirectToRoute("fos_user_security_login");
    }

    /**
     * @Route("/{_locale}", name="homepage")
     */
    public function indexAction(Request $request,$_locale='en')
    {$user = $this->getUser();
        if($user){

            if($user->getType()=="chauffeur"){
                return $this->redirectToRoute("chauffeur_homepage");
            }
            if($user->getType()=="client"){
                return $this->redirectToRoute("client_homepage");
            }
            if($user->getType()=="admin"){
                return $this->render('indexBack.html.twig');
                return $this->redirectToRoute("client_homepage");
            }

        }

        // replace this example code with whatever you need
        return $this->render('templateFront/index.html.twig');
    }

    /**
     * @Route("/GPS/{lat}/{lon}", name="updateGPS")
     */
    public function updateGPS($lat,$lon)
    {
        $user = $this->getUser();
        if($user!=null){
            $id=$user->getId();
            $user=$this->getDoctrine()->getRepository(user::class)->find($id);
            $user->setLongitude($lon);
            $user->setLatitude($lat);
            $this->getDoctrine()->getManager()->flush();
        }

        // replace this example code with whatever you need
        return new Response("ok");
    }

    /**
     * @Route("/AdreeseGPS/{lat}/{lon}", name="adresseGPS")
     */

    public function getAdresse($lat,$lon)
    {
        $client = HttpClient::create();
        $response = $client->request('GET', "https://nominatim.openstreetmap.org/reverse?format=json&lat=".$lat."&lon=".$lon);

        $statusCode = $response->getStatusCode();
// $statusCode = 200
        $contentType = $response->getHeaders()['content-type'][0];
// $contentType = 'application/json'
        $content = $response->getContent();
// $content = '{"id":521583, "name":"symfony-docs", ...}'
        $content = $response->toArray();
        return new Response($content["display_name"]);
    }

    /**
     * @Route("/setUser/{username}/{mail}/{password}/{numero}/{id}", name="setUser")
     */

    public function setUser($username,$mail,$password,$numero,$id)
    {
        $userManager = $this->get('fos_user.user_manager');
        $data="oui";
        // Or you can use the doctrine entity manager if you want instead the fosuser manager
        // to find
        //$em = $this->getDoctrine()->getManager();
        //$usersRepository = $em->getRepository("mybundleuserBundle:User");
        // or use directly the namespace and the name of the class
        // $usersRepository = $em->getRepository("mybundle\userBundle\Entity\User");
        //$email_exist = $usersRepository->findOneBy(array('email' => $email));
        $user=new user();
        $user=$this->getDoctrine()->getRepository($user)->find($id);

        $email_exist = $userManager->findUserByEmail($mail);
        $username_exist = $userManager->findUserByUsername($username);

        // Check if the user exists to prevent Integrity constraint violation error in the insertion
        $data="oui";
        if($email_exist && $mail!=$user->getMail()){
            $data = [ "requette"=>['reponse'=>'no','message'=>"cette email existe deja"]];
        }
        if($username_exist && $username!=$user->getUsername()){
            $data = [ "requette"=>['reponse'=>'no','message'=>"ce username existe deja"]];
        }
        if(!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
            $data = [ "requette"=>['reponse'=>'no','message'=>"email incorrect"]];
        }

        if(data=="oui"){
            $encoder_service = $this->get('security.encoder_factory');
            $encoder = $encoder_service->getEncoder($user);
            $data = [ "requette"=>['reponse'=>'no']];
            if($encoder->isPasswordValid($user->getPassword(),$password,$user->getSalt())){
                $data = [ "requette"=>['reponse'=>'yes','user'=>['username' =>$user->getUsername(),'email' =>$user->getEmail(),'id_user'=>$user->getId(),'type'=>$user->getType()]]];

                

            }

        }


    }

}

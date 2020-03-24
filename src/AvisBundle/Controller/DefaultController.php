<?php

namespace AvisBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('AvisBundle:Default:index.html.twig');
    }
}

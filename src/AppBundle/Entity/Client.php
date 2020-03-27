<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Client
 *
 * @ORM\Table(name="Client")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\ClientRepository")
 */
class Client
{
/**
     * @var int
     * @ORM\Id
     * @ORM\Column(name="id" , type="integer")
     */
    private $idUser;



    /**
     * Set idUser
     *
     * @param integer $idUser
     *
     * @return Client
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return int
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    public function __toString()
    {
        return strval($this->idUser);
    }
}


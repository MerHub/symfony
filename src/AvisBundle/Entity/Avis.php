<?php

namespace AvisBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Avis
 *
 * @ORM\Table(name="Avis")
 * @ORM\Entity(repositoryClass="AvisBundle\Repository\AvisRepository")
 */
class Avis
{
    /**
     * @var int
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(name="id_Avis", type="integer")
     */
    private $idAvis;

    /**
     * @var int
     * @Assert\NotBlank
     * @ORM\OneToOne(targetEntity=AppBundle\Entity\chauffeur::class)
     * @ORM\JoinColumn(name="id_chauffeur" , referencedColumnName="id")
     */
    private $idChauffeur;

    /**
     * @var int
     * @Assert\NotBlank
     * @ORM\OneToOne(targetEntity=AppBundle\Entity\Client::class)
     * @ORM\JoinColumn(name="id_client" , referencedColumnName="id")
     */
    private $idCclient;

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\Length(min=5,message=" length of the message must be between 5 and 10 character")
     * @Assert\Length(max=100,message=" length of the message must be between 5 and 10 character")
     * @ORM\Column(name="msg", type="string", length=255)
     */
    private $msg;

    /**
     * @var int
     * @Assert\NotBlank
     * @Assert\LessThanOrEqual(5 ,message=" note must be between 1 and 5 ")
     * @Assert\GreaterThanOrEqual(0,message=" note must be between 1 and 5 )
     * @ORM\Column(name="note", type="integer")
     */
    private $note;


    /**
     * Get id
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idAvis
     * @param integer $idAvis
     *
     * @return Avis
     */
    public function setIdAvis($idAvis)
    {
        $this->idAvis = $idAvis;

        return $this;
    }

    /**
     * Get idAvis
     *
     * @return int
     */
    public function getIdAvis()
    {
        return $this->idAvis;
    }

    /**
     * Set idChauffeur
     *
     * @param integer $idChauffeur
     *
     * @return Avis
     */
    public function setIdChauffeur($idChauffeur)
    {
        $this->idChauffeur = $idChauffeur;

        return $this;
    }

    /**
     * Get idChauffeur
     *
     * @return int
     */
    public function getIdChauffeur()
    {
        return $this->idChauffeur;
    }

    /**
     * @return int
     */
    public function getIdCclient()
    {
        return $this->idCclient;
    }

    /**
     * @param int $idCclient
     */
    public function setIdCclient($idCclient)
    {
        $this->idCclient = $idCclient;
    }

    /**
     * @return string
     */
    public function getMsg()
    {
        return $this->msg;
    }

    /**
     * @param string $msg
     */
    public function setMsg($msg)
    {
        $this->msg = $msg;
    }

    /**
     * @return int
     */
    public function getNote()
    {
        return $this->note;
    }

    /**
     * @param int $note
     */
    public function setNote($note)
    {
        $this->note = $note;
    }


}


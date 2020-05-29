<?php

namespace ReclamationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * reclamation
 *
 * @ORM\Table(name="reclamation")
 * @ORM\Entity(repositoryClass="ReclamationBundle\Repository\reclamationRepository")
 */
class reclamation
{
    /**
     * @var int
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(name="id_rec", type="integer")
     */
    private $idRec;


    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="typeReclamation")
     * @ORM\JoinColumn(name="type_Reclamation", referencedColumnName="id")
     */
    private $typeReclamation;

    /**
     * @var int
     *
     * @ORM\Column(name="etat", type="smallint")
     */
    private $etat;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateAjout", type="datetime")
     */
    private $dateAjout;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\user")
     * @ORM\JoinColumn(name="id_client" , referencedColumnName="id_user")
     */
    private $idClient;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\user")
     * @ORM\JoinColumn(name="id_chauffeur" , referencedColumnName="id_user")
     */
    private $idChauffeur;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->idRec;
    }

    /**
     * Set idRec
     *
     * @param integer $idRec
     *
     * @return reclamation
     */
    public function setIdRec($idRec)
    {
        $this->idRec = $idRec;

        return $this;
    }

    /**
     * Get idRec
     *
     * @return int
     */
    public function getIdRec()
    {
        return $this->idRec;
    }


    /**
     * Set typeReclamation
     *
     * @param int $typeReclamation
     *
     * @return reclamation
     */
    public function setTypeReclamation($typeReclamation)
    {
        $this->typeReclamation = $typeReclamation;

        return $this;
    }

    /**
     * Get typeReclamation
     *
     * @return int
     */
    public function getTypeReclamation()
    {
        return $this->typeReclamation;
    }

    /**
     * Set etat
     *
     * @param integer $etat
     *
     * @return reclamation
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;

        return $this;
    }

    /**
     * Get etat
     *
     * @return int
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * Set dateAjout
     *
     * @param \DateTime $dateAjout
     *
     * @return reclamation
     */
    public function setDateAjout($dateAjout)
    {
        $this->dateAjout = $dateAjout;

        return $this;
    }

    /**
     * Get dateAjout
     *
     * @return \DateTime
     */
    public function getDateAjout()
    {
        return $this->dateAjout;
    }

    /**
     * Set idClient
     *
     * @param integer $idClient
     *
     * @return reclamation
     */
    public function setIdClient($idClient)
    {
        $this->idClient = $idClient;

        return $this;
    }

    /**
     * Get idClient
     *
     * @return int
     */
    public function getIdClient()
    {
        return $this->idClient;
    }

    /**
     * Set idChauffeur
     *
     * @param integer $idChauffeur
     *
     * @return reclamation
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



}


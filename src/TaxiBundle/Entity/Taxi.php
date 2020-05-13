<?php

namespace TaxiBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Taxi
 *
 * @ORM\Table(name="Taxi")
 * @ORM\Entity(repositoryClass="TaxiBundle\Repository\TaxiRepository")
 */
class Taxi
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_Taxi", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idTaxi;

    /**
     * @var integer
     * @ORM\OneToOne(targetEntity=AppBundle\Entity\user::class)
     * @ORM\JoinColumn(name="id_chauffeur" , referencedColumnName="id_user")
     */
    private $idChauffeur;

    /**
     * @var string
     * @ORM\ManyToOne(targetEntity=CategorieTaxi::class)
     * @ORM\JoinColumn(name="categorie" , referencedColumnName="id")
     */
    private $categorie;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=false)
     */
    private $photo;

    /**
     * @var string
     *
     * @ORM\Column(name="num_chassis", type="string", length=255, nullable=false)
     */
    private $numChassis;

    /**
     * @return string
     */
    public function getCategorie()
    {
        return $this->categorie;
    }

    /**
     * @param string $categorie
     */
    public function setCategorie($categorie)
    {
        $this->categorie = $categorie;
    }

    /**
     * @return int
     */
    public function getIdTaxi()
    {
        return $this->idTaxi;
    }

    /**
     * @param int $idTaxi
     */
    public function setIdTaxi($idTaxi)
    {
        $this->idTaxi = $idTaxi;
    }

    /**
     * @return int
     */
    public function getIdChauffeur()
    {
        return $this->idChauffeur;
    }

    /**
     * @param int $idChauffeur
     */
    public function setIdChauffeur($idChauffeur)
    {
        $this->idChauffeur = $idChauffeur;
    }

    /**
     * @return string
     */
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     * @param string $photo
     */
    public function setPhoto($photo)
    {
        $this->photo = $photo;
    }

    /**
     * @return string
     */
    public function getNumChassis()
    {
        return $this->numChassis;
    }

    /**
     * @return string
     */
    public function getCategorie()
    {
        return $this->categorie;
    }

    /**
     * @param string $categorie
     */
    public function setCategorie($categorie)
    {
        $this->categorie = $categorie;
    }


    /**
     * @param string $numChassis
     */
    public function setNumChassis($numChassis)
    {
        $this->numChassis = $numChassis;
    }


}


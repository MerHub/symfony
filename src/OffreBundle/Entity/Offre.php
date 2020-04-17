<?php

namespace OffreBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

use Symfony\Component\Validator\Constraints as Assert;
/**
 * Offre
 *
 * @ORM\Table(name="Offre")
 * @ORM\Entity(repositoryClass="OffreBundle\Repository\OffreRepository")
 */
class Offre
{


    /**
     * @var int
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(name="id_Offre", type="integer")
     */
    private $idOffre;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_d", type="datetime")
     * @Assert\GreaterThan("today")
     */
    private $dateD;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_f", type="datetime")
     * @Assert\GreaterThan("today")
     */
    private $dateF;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;

    /**
     * @var float
     *
     * @ORM\Column(name="reduction_Offre", type="float")
     */
    private $reductionOffre;

    /**
     * @var string
     *
     * @ORM\Column(name="code_promo", type="string", length=255)
     */
    private $codePromo;

    /**
     * @var string
     *
     * @ORM\Column(name="img", type="string", length=255)
     */
    private $img;




    /**
     * Set idOffre
     *
     * @param integer $idOffre
     *
     * @return Offre
     */
    public function setIdOffre($idOffre)
    {
        $this->idOffre = $idOffre;

        return $this;
    }

    /**
     * Get idOffre
     *
     * @return int
     */
    public function getIdOffre()
    {
        return $this->idOffre;
    }

    /**
     * Set dateD
     *
     * @param \DateTime $dateD
     *
     * @return Offre
     */
    public function setDateD($dateD)
    {
        $this->dateD = $dateD;

        return $this;
    }

    /**
     * Get dateD
     *
     * @return \DateTime
     */
    public function getDateD()
    {
        return $this->dateD;
    }

    /**
     * Set dateF
     *
     * @param \DateTime $dateF
     *
     * @return Offre
     */
    public function setDateF($dateF)
    {
        $this->dateF = $dateF;

        return $this;
    }

    /**
     * Get dateF
     *
     * @return \DateTime
     */
    public function getDateF()
    {
        return $this->dateF;
    }

    /**
     * Set type
     *
     * @param string $type
     *
     * @return Offre
     */
    public function setType($type)
    {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Offre
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set reductionOffre
     *
     * @param float $reductionOffre
     *
     * @return Offre
     */
    public function setReductionOffre($reductionOffre)
    {
        $this->reductionOffre = $reductionOffre;

        return $this;
    }

    /**
     * Get reductionOffre
     *
     * @return float
     */
    public function getReductionOffre()
    {
        return $this->reductionOffre;
    }

    /**
     * Set codePromo
     *
     * @param string $codePromo
     *
     * @return Offre
     */
    public function setCodePromo($codePromo)
    {
        $this->codePromo = $codePromo;

        return $this;
    }

    /**
     * Get codePromo
     *
     * @return string
     */
    public function getCodePromo()
    {
        return $this->codePromo;
    }

    /**
     * Set img
     *
     * @param string $img
     *
     * @return Offre
     */
    public function setImg($img)
    {
        $this->img = $img;

        return $this;
    }

    /**
     * Get img
     *
     * @return string
     */
    public function getImg()
    {
        return $this->img;
    }
}


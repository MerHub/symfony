<?php

namespace TaxiBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * CategorieTaxi
 *
 * @ORM\Table(name="categorie_taxi")
 * @ORM\Entity(repositoryClass="TaxiBundle\Repository\CategorieTaxiRepository")
 */
class CategorieTaxi
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var int
     *
     * @ORM\Column(name="model", type="integer")
     */
    private $model;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255)
     */
    private $image;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set model
     *
     * @param integer $model
     *
     * @return CategorieTaxi
     */
    public function setModel($model)
    {
        $this->model = $model;

        return $this;
    }

    /**
     * Get model
     *
     * @return int
     */
    public function getModel()
    {
        return $this->model;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return CategorieTaxi
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }
}


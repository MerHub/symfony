<?php

namespace AppBundle\Entity;

use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * user
 *
 * @ORM\Table(name="user")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\userRepository")
 * @UniqueEntity(
 *     fields={"nTel"},
 *     errorPath="nTel",
 *     message="this number already exist"
 * )
 */
class user extends BaseUser
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    protected $id;

    protected $email;

    /**
     * @var integer
     * @Assert\Regex(
     *     pattern="/^[0-9]{8}$/",
     *     match=true,
     *     message="incorrect phone number "
     * )
     * @ORM\Column(name="n_tel", type="integer", nullable=true)
     */
    protected $nTel;

    /**
     * @var float
     *
     * @ORM\Column(name="latitude", type="float", precision=10, scale=0, nullable=true)
     */
    protected $latitude;

    /**
     * @var float
     *
     * @ORM\Column(name="longitude", type="float", precision=10, scale=0, nullable=true)
     */
    protected $longitude;
    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", nullable=false)
     */
    protected $type;

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return int
     */
    public function getNTel()
    {
        return $this->nTel;
    }

    /**
     * @param int $nTel
     */
    public function setNTel($nTel)
    {
        $this->nTel = $nTel;
    }




    /**
     * @return float
     */
    public function getLongitude()
    {
        return $this->longitude;
    }

    /**
     * @return float
     */
    public function getLatitude()
    {
        return $this->latitude;
    }

    /**
     * @return mixed
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @return string
     */
    public function getMail()
    {
        return $this->email;
    }

    /**
     * @param string $email
     */
    public function setMail($email)
    {
        $this->email = $mail;
    }

    /**
     * @param string $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @param mixed $email
     */
    public function setEmail($email): void
    {
        $this->email = $email;
    }

    /**
     * @param float $latitude
     */
    public function setLatitude(float $latitude): void
    {
        $this->latitude = $latitude;
    }

    /**
     * @param float $longitude
     */
    public function setLongitude(float $longitude): void
    {
        $this->longitude = $longitude;
    }



    public function __construct()
    {
        parent::__construct();
        // your own logic
    }


}


<?php

namespace VeloBundle\Form;

use AppBundle\Entity\Client;
use AppBundle\Entity\user;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use VeloBundle\Entity\Velo;

class LocationType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('dateD',DateTimeType::class)->add('dateF',DateTimeType::class)->add('prix')->add('idVelo',EntityType::class,
            [
                'class'=>Velo::class,
                'choice_label'=>'id',
                "attr"=>["class"=>"dispa"]
            ])->add('idClient',EntityType::class,
            [
                'class'=>user::class,
                'choice_label'=>'id',
                "attr"=>["class"=>"dispa"]
            ]);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'VeloBundle\Entity\Location'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'velobundle_location';
    }


}

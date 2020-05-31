<?php

namespace TaxiBundle\Form;

use AppBundle\Entity\chauffeur;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use TaxiBundle\Entity\CategorieTaxi;

class TaxiType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('photo',FileType::class,array('label'=>'pick image','data_class' => null))->add('numChassis')->add('categorie',EntityType::class,[
            "class"=>CategorieTaxi::class,
            "choice_label"=>"model"
        ]);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'TaxiBundle\Entity\Taxi'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'taxibundle_taxi';
    }


}

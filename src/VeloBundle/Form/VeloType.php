<?php

namespace VeloBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class VeloType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('type',ChoiceType::class,array('choices'=>array('Electrique bic'=>'electrique bic','Bic'=>'bic'))
            )->add('adresse',null,[
            'attr'=>[
                'class'=>'dontWrite'
            ]
        ])->add('qte')->add('photo',FileType::class,array('label'=>'pick image','data_class' => null))->add('prix')->add('latitude',HiddenType::class,['required'=>true])->add('longitude',HiddenType::class,['required'=>true]);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'VeloBundle\Entity\Velo'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'velobundle_velo';
    }


}

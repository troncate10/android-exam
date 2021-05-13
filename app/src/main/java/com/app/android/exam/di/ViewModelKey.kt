package com.app.android.exam.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
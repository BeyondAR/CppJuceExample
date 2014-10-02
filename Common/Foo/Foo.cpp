/*
  ==============================================================================

    Foo.cpp
    Created: 24 Sep 2014 11:29:17pm
    Author:  Joan Puig Sanz

  ==============================================================================
*/

#include "Foo.h"
#include "../Util/Log.h"

Foo::Foo()    : _counter (0)
{}

Foo::~Foo()
{
	LOG_D ("Calling Foo destructor " + String (_counter));
}

String Foo::getString()
{
    _counter ++;
    
    return "Hello form C++!!  " + String (_counter);
}


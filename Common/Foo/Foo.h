/*
  ==============================================================================

    Foo.h
    Created: 24 Sep 2014 11:29:17pm
    Author:  Joan Puig Sanz

  ==============================================================================
*/

#ifndef FOO_H_INCLUDED
#define FOO_H_INCLUDED

#include "../CommonJuceHeader.h"
#include "Bar/Bar.h"

class Foo {
public:
    Foo();
    ~Foo();
    
    String getString();
    
private:
    int _counter;
    Bar _bar;
};



#endif  // FOO_H_INCLUDED

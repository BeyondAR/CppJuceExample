//
//  JPSExampleCpp.m
//  Juce-example-ios
//
//  Created by Joan Puig Sanz on 24/09/14.
//  Copyright (c) 2014 Joan Puig Sanz. All rights reserved.
//

#import "JPSExampleCpp.h"
#import "../../Common/Foo/Foo.h"
#include "../../Common/CommonJuceHeader.h"

@implementation JPSExampleCpp

Foo fooObject;

-(id) init
{
    self = [super init];
    if (self) {
        
    }
    return self;
}

- (NSString*) stringFromCpp
{
    
    NSString* result = [NSString stringWithUTF8String:fooObject.getString().toRawUTF8()];
    
    return result;
}

@end

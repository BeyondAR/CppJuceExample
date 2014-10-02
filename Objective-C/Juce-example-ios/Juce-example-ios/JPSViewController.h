//
//  JPSViewController.h
//  Juce-example-ios
//
//  Created by Joan Puig Sanz on 24/09/14.
//  Copyright (c) 2014 Joan Puig Sanz. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "JPSExampleCpp.h"

@interface JPSViewController : UIViewController
{
    JPSExampleCpp* cppBinding;
}

@end

//
//  JPSViewController.m
//  Juce-example-ios
//
//  Created by Joan Puig Sanz on 24/09/14.
//  Copyright (c) 2014 Joan Puig Sanz. All rights reserved.
//

#import "JPSViewController.h"


@interface JPSViewController ()
@property (weak, nonatomic) IBOutlet UITextView *outCppText;
@end

JPSExampleCpp *cppBinder;

@implementation JPSViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    cppBinder =  [[JPSExampleCpp alloc] init];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)onDoSomethingCpp:(id)sender {
    NSString *textFromCpp = [cppBinder stringFromCpp];
    [_outCppText setText:textFromCpp];
}

@end

#!/usr/bin/perl
use strict;
use warnings;
# Using core modules to simulate a data export from a legacy system
use JSON;

print "====================================================\n";
print "   TaskFlow API - Legacy Perl Export Simulation    \n";
print "====================================================\n";

# Simulating database records fetched from an old PERL system
my @legacy_tasks = (
    {
        title       => "Migrate authentication modules",
        description => "Move old perl session validation to modern Spring Security",
        status      => "PENDING"
    },
    {
        title       => "Refactor database triggers",
        description => "Translate direct SQL procedures into Spring Data JPA repositories",
        status      => "IN_PROGRESS"
    }
);

# Converting the Perl array data into a clean JSON string
my $json_output = encode_json(\@legacy_tasks);

print "\n[SUCCESS] Legacy data successfully exported to JSON format:\n\n";
print $json_output . "\n\n";
print "====================================================\n";
print "Ready to be consumed by the new Spring Boot REST API.\n";
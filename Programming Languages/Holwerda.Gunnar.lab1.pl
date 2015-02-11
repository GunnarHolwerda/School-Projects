#########################################
#    CSCI 305 - Programming Lab #1
#
#  < Gunnar Holwerda >
#  < GunnarHolwerda@gmail.com >
#
#########################################

# Replace the string value of the following variable with your names.
my $name    = "Gunnar Holwerda";
my $partner = "no one else";
print "CSCI 305 Lab 1 submitted by $name and $partner.\n\n";

# Checks for the argument, fail if none given
if ( $#ARGV != 0 ) {
    print STDERR "You must specify the file name as the argument.\n";
    exit 4;
}

# Opens the file and assign it to handle INFILE
open( INFILE, $ARGV[0] ) or die "Cannot open $ARGV[0]: $!.\n";

# YOUR VARIABLE DEFINITIONS HERE...
my @special_chars = (
    "\\(",  "\\[", "\\{", "\\\\", "\/",  "_", "-", ":",
    "\\\"", "`",   "\\+", "=",    "\\*", "feat."
);
my @punctuation
    = ( "\\?", "¿", "!", "¡", "\\.", ";", "&", "\$", "@", "%", "#", "|" );
%word_hash = ();

# This loops through each line of the file
while ( my $line = <INFILE> ) {

    # Remove everything before the Song title here
    $line =~ s/(.*<SEP>)//;

    # Sets the title to all lower case
    $line = lc $line;

# Iterates over array of special characters and removes all text following them
    foreach $char (@special_chars) {
        $line =~ s/($char.*)//;
    }

    # Iterates over array of punctuation and removes all of them
    foreach $punc (@punctuation) {
        $line =~ s/($punc)//g;
    }

    # Match all songs that do not contain only English characters
    if ( $line =~ m/[^\w\s']+/ ) {
    }
    else {
        # Split the line into an array based on a space delimiter
        @words = split( ' ', $line );

        for ( $i = 0; $i < @words; $i++ ) {
            if ( ( $i + 1 ) < @words ) {

      # Create hash of words for each word and add 1 to the count of that word
                $word_hash{ $words[$i] }{ $words[ $i + 1 ] } += 1;
            }

            # This increases count of current word
            $word_hash{ $words[$i] }{'count'} += 1;
        }

    }
}

# Close the file handle
close(INFILE);

# At this point (hopefully) you will have finished processing the song
# title file and have populated your data structure of bigram counts.
print "File parsed. Bigram model built.\n\n";

mcw("party");

# # User control loop
# print "Enter a word [Enter 'q' to quit]: ";
# $input = <STDIN>;
# chomp($input);
# print "\n";
# while ( $input ne "q" ) {

#     # Replace these lines with some useful code
#     print "Not yet implemented.  Goodbye.\n";
#     $input = 'q';
# }

# MORE OF YOUR CODE HERE....

sub mcw {

    # Get total number of arguments passed
    $argument = $_[0];

    for ( $i = 0; $i < 20; $i++ ) {
    	print "$argument ";
        $key = (
            sort { $word_hash{$argument}{$b} <=> $word_hash{$argument}{$a} }
                keys %{ $word_hash{$argument} }
        )[1];
        # print "$word_hash{$argument}{$key} ";
        $argument = $key;
    }
    print "\n";
}

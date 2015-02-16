#########################################
#    CSCI 305 - Programming Lab #1
#
#  < Gunnar Holwerda >
#  < GunnarHolwerda@gmail.com >
#
#########################################

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

# Variables
my @special_chars = (
    "\\(",  "\\[", "\\{", "\\\\", "\/",  "_", "-", ":",
    "\\\"", "`",   "\\+", "=",    "\\*", "feat."
);
my @punctuation
    = ( "\\?", "¿", "!", "¡", "\\.", ";", "&", "\$", "@", "%", "#", "|" );
my @stop_words = (
    "a",  "an", "and", "the", "with", "from", "for", "out",
    "by", "in", "of",  "on",  "or",   "to"
);
%word_hash = ();

# This loops through each line of the file
while ( my $line = <INFILE> ) {

    # Remove everything before the Song title here
    $line =~ s/(.*<SEP>)//;

    # Sets the title to all lower case
    $line = lc $line;

    # Iterates over array of special characters and removes all text following
    foreach $char (@special_chars) {
        $line =~ s/($char.*)//;
    }

    # Trim beginning and ending whitespace from string
    $line =~ s/^\s+|\s+$//g;

    # Iterate over the stop words and remove them from the song title
    foreach $stop_word (@stop_words) {

        # Remove the word
        $line =~ s/( $stop_word )//;
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

            # Don't count the stop words
            if (    !grep( /^$words[$i]$/, @stop_words )
                and !grep( /^$words[$i + 1]$/, @stop_words ) )
            {
                # Create hash of words for each word and add 1 to the count
                if ( ( $i + 1 ) < @words ) {
                    $word_hash{ $words[$i] }{ $words[ $i + 1 ] } += 1;
                }

                # This increases count of current word
                $word_hash{ $words[$i] }{'_'} += 1;
            }
        }

    }
}

# Close the file handle
close(INFILE);

#print_hash();

print "File parsed. Bigram model built.\n\n";

# User control loop
while ( $input ne "q" ) {
    print "Enter a word [Enter 'q' to quit]: ";
    $input = <STDIN>;
    chomp($input);
    print "\n";

    if ( $input eq "q" ) {
        exit;
    }

    mcw($input);
}

sub mcw {

    # Get total number of arguments passed
    my $argument   = $_[0];
    my $song_title = "$argument ";
    my $end_next   = 0;

    for ( $i = 0; $i < 20; $i++ ) {
        @tie_words   = ();
        $word_to_get = 1;

        # Get the second element from the array because the first will always
        # be the number of times the word appears
        my @keys = (
            sort { $word_hash{$argument}{$b} <=> $word_hash{$argument}{$a} }
                keys %{ $word_hash{$argument} }
        );

        # Remove the count from the array which will always be the top item
        shift(@keys);

        # Create a list of all words with the same count
        foreach $key (@keys) {

            # Compare count of current word to next word
            if ( $word_hash{$argument}{$key}
                == $word_hash{$argument}{ $keys[$word_to_get] } )
            {
                # If they are the same push them to tie words array
                push( @tie_words, $key );
                push( @tie_words, $keys[$word_to_get] );
                $word_to_get += 1;
            }
            else {
                # Else we can bail out
                last;
            }
        }

        # If tie_words is empty then there is only one word next
        if ( @tie_words == 0 ) {
            $new_argument = $keys[0];
        }
        else {
            # Else we need to randomly pick a word from the tie_words
            $new_argument = $tie_words[ int( rand(@tie_words) ) ];
        }

        # If end_next set to 1 then we exit here to stop loops
        if ($end_next) {
            last;
        }

        $song_title .= "$new_argument ";

        # Check if next words most popular word is this word
        $next_value = (
            sort {
                $word_hash{$new_argument}{$b} <=> $word_hash{$new_argument}
                    {$a}
                }
                keys %{ $word_hash{$new_argument} }
        )[1];
        if ( $argument eq $next_value ) {
            $end_next = 1;
        }

        $argument = $new_argument;
    }

    print "$song_title\n";
}

sub print_hash {
    foreach $word ( keys %word_hash ) {
        $distinct_word_count = ( keys $word_hash{$word} ) - 1;
        print "$word: $word_hash{$word}{'_'} $distinct_word_count\n";
        foreach $following_word (
            sort { $word_hash{$word}{$b} <=> $word_hash{$word}{$a} }
            keys %{ $word_hash{$word} }
            )
        {
            if ( $following_word ne "_" ) {
                print
                    "\t$following_word; count = $word_hash{$word}{$following_word}\n";
            }
        }
    }
}

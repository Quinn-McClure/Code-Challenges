import 'dart:io'; 

const version = '0.0.1';
void main(List<String> arguments) {
  if(arguments.isEmpty || arguments.first == 'help') {
    printUsage();
  } else if (arguments.first == 'version') {
    print('Dartpedia CLI version $version');
  } else if (arguments.first == 'search') {
    final inputArgs = arguments.length > 1 ? arguments.sublist(1) : null;
    searchArtciles(inputArgs);
  } else {
    printUsage();
  }
}

void printUsage() {
  print (
    "The following commands are valid: 'help', 'version', and 'search <ARTICLE-TITLE>"
  );
}

void searchArtciles(List<String>? arguments) {
  final String articleTitle;

  if(arguments == null || arguments.isEmpty) {
    print('Please provide an article title to search');

    //await article tite
    articleTitle = stdin.readLineSync() ?? '';
  } else {
    articleTitle = arguments.join(' ');
  }

  print("Article Title: $articleTitle");
  print("Looking up $articleTitle");
}

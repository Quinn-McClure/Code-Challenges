import 'dart:io';
import 'package:http/http.dart' as http;

const version = '0.0.1';
void main(List<String> arguments) {
  if(arguments.isEmpty || arguments.first == 'help') {
    printUsage();
  } else if (arguments.first == 'version') {
    print('Dartpedia CLI version $version');
  } else if (arguments.first == 'search') {
    final inputArgs = arguments.length > 1 ? arguments.sublist(1) : null;
    searchArticles(inputArgs);
  } else {
    printUsage();
  }
}

void printUsage() {
  print (
    "The following commands are valid: 'help', 'version', and 'search <ARTICLE-TITLE>"
  );
}

Future<String> getWikipediaArticle(String articleName) async{
  final url = Uri.https(
    'en.wikipedia.org',
    '/api/rest_v1/page/summary/$articleName'
  );

  final response = await http.get(url);

  if (response.statusCode == 200) {
    return response.body;
  }

  return 'Error: Failed to fetch article $articleName';
}

void searchArticles(List<String>? arguments) async {
  final String articleTitle;

  if(arguments == null || arguments.isEmpty) {
    print('Please provide an article title to search');

    //await article title
    articleTitle = stdin.readLineSync() ?? '';
  } else {
    articleTitle = arguments.join(' ');
  }

  print("Article Title: $articleTitle");
  print("Looking up $articleTitle");
}

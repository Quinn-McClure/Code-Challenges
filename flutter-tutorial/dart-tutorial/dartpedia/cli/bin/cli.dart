import 'dart:io';
import 'package:http/http.dart' as http;
import 'package:command_runner/command_runner.dart';

const version = '0.0.1';
void main(List<String> arguments) async {
  var commandRunner = CommandRunner()..addCommand(HelpCommand());
  await commandRunner.run(arguments);
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
    if (articleTitle == null || articleTitle.isEmpty) {
      print("No article name provided");
      return;
    }
  } else {
    articleTitle = arguments.join(' ');
  }

  print("Article Title: $articleTitle");
  
  var articleContent = await getWikipediaArticle(articleTitle);
  print (articleContent);
}

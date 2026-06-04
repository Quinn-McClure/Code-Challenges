import 'dart:async'; 
import 'dart:collection';
import 'dart:io';
import 'arguments.dart';
import 'exceptions.dart';

class CommandRunner {
  CommandRunner({this.onError});
  
  final Map<String, Command> _commands = <String, Command>{};

  UnmodifiableSetView<Command> get commands => 
    UnmodifiableSetView<Command>(<Command>{..._commands.values});

  FutureOr<void> Function(Object)? onError;

  Future<void> run(List<String> input) async {
    try {
      final ArgResults results = parse(input);
    
      if(results.command != null) {
        Object? output = await results.command!.run(results);
        print(output.toString());
      }
    } on Exception catch (exception) {
      if (onError != null) {
        onError!(exception);
      } else {
        rethrow;
      }
    }
  }

  void addCommand(Command command) {
    //TODO handle error (Commands can't have names that conflict)
    _commands[command.name] = command;
    command.runner = this;
  }

  ArgResults parse(List<String> input) {
    ArgResults results = ArgResults();
    if (input.isEmpty) return results;

    if(_commands.containsKey(input.first)) {
      results.command = _commands[input.first];
      input = input.sublist(1);
    } else {
      throw ArgumentException(
        'The first word of input must be a command.',
        null,
        input.first,
      );
    }

    if (results.command != null && input.isNotEmpty && _commands.containsKey(input.first)) {
      throw ArgumentException(
        'Input can only contain one command. Got ${input.first} and ${results.command!.name}',
        null,
        input.first,
      );
    }

    Map<Option, Object?> inputOptions = {};
    int i = 0;
        while (i < input.length) {
      if (input[i].startsWith('-')) {
        final base = _removeDash(input[i]);

        final option = results.command!.options.firstWhere(
          (option) => option.name == base || option.abbr == base,
          orElse: () {
            throw ArgumentException(
              'Unknown option ${input[i]}',
              results.command!.name,
              input[i],
            );
          },
        );

        if (option.type == OptionType.flag) {
          inputOptions[option] = true;
          i++;
          continue;
        }

        if (option.type == OptionType.option) {
          if (i + 1 >= input.length) {
            throw ArgumentException(
              'Option ${option.name} requires an argument',
              results.command!.name,
              option.name,
            );
          }

          if (input[i + 1].startsWith('-')) {
            throw ArgumentException(
              'Option ${option.name} requires an argument, but got another option ${input[i + 1]}',
              results.command!.name,
              option.name,
            );
          }

          inputOptions[option] = input[i + 1];
          i += 2;
          continue;
        }
      }

      if (results.commandArg != null && results.commandArg!.isNotEmpty) {
        throw ArgumentException(
          'Commands can only have up to one argument.',
          results.command!.name,
          input[i],
        );
      }
      results.commandArg = input[i];
      i++;
    }

    results.options = inputOptions;
    return results;
      
  }

  String _removeDash(String input) {
  if (input.startsWith('--')) {
    return input.substring(2);
  }
  if (input.startsWith('-')) {
    return input.substring(1);
  }
  return input;
}

  // Returns usage for the executable only.
  // Should be overridden if you aren't using [HelpCommand]
  // or another means of printing usage.

  String get usage {
    final exeFile = Platform.script.path.split('/').last;
    return 'Usage: dart bin/$exeFile <command> [commandArg?] [...options?]';
  }
}
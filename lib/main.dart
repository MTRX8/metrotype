import 'package:flutter/material.dart';

void main() => runApp(const MainApp());

///
class MainApp extends StatelessWidget {
  ///
  const new({super.key});

  @override
  Widget build(BuildContext context) {
    return WidgetsApp(
      title: 'MetroType',
      debugShowCheckedModeBanner: false,
      color: const Color(0xFF000000),
      home: const Center(child: Text('MetroType')),
      pageRouteBuilder: <T>(settings, builder) => PageRouteBuilder<T>(
        pageBuilder: (context, _, _) => builder(context),
      ),
    );
  }
}

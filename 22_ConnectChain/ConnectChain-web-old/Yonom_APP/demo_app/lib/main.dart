import 'package:flutter/material.dart';

import './screens/start_screen.dart';
import './screens/tabs_screen.dart';
import '../screens/sell_list_screen.dart';

void main() {
  runApp(
    MaterialApp(
      title: 'Carrot Market Clone',
      initialRoute: '/',
      routes: {
        // '/': (context) => StartScreen(),
        '/': (context) => TabsScreen(),
        SellListScreen.routeName: (ctx) => SellListScreen(),
      },
      theme: ThemeData(
        accentColor: Color.fromRGBO(155, 89, 182, 1),
      ),
    ),
  );
}

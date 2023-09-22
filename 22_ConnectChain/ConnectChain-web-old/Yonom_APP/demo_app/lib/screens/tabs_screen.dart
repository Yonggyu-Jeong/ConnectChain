import 'package:flutter/material.dart';

import '../screens/home_screen.dart';
import '../screens/chat_list_screen.dart';
import '../screens/my_page_screen.dart';

class TabsScreen extends StatefulWidget {
  @override
  State<TabsScreen> createState() => _TabsScreenState();
}

class _TabsScreenState extends State<TabsScreen> {
  final List<Map<String, Object>> _pages = [
    {
      'page': HomeScreen(),
      'title': '체인마켓',
      'actions': <Widget>[
        IconButton(
          visualDensity: VisualDensity(horizontal: -3),
          onPressed: null,
          icon: const Icon(
            Icons.search,
            color: Colors.black,
          ),
        ),
        IconButton(
          visualDensity: VisualDensity(horizontal: -3),
          onPressed: null,
          icon: const Icon(
            Icons.menu,
            color: Colors.black,
          ),
        ),
      ],
      'isFloating': true,
    },
    {
      'page': ChatListScreen(),
      'title': '채팅',
      'actions': <Widget>[],
      'isFloating': false,
    },
    {
      'page': MyPageScreen(),
      'title': '마이페이지',
      'actions': <Widget>[
        IconButton(
          visualDensity: VisualDensity(horizontal: -3),
          onPressed: null,
          icon: const Icon(
            Icons.settings,
            color: Colors.black,
          ),
        ),
      ],
      'isFloating': false,
    },
  ];

  int _selectedPageIndex = 0;

  void _selectPage(int index) {
    setState(() {
      _selectedPageIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: false,
        backgroundColor: Colors.white,
        foregroundColor: Colors.black,
        elevation: 0,
        shape: Border(
          bottom: BorderSide(
            color: Colors.black26,
          ),
        ),
        title: Text(
          _pages[_selectedPageIndex]['title'] as String,
          style: TextStyle(fontSize: 20, fontWeight: FontWeight.w900),
        ),
        actions: _pages[_selectedPageIndex]['actions'] as List<Widget>,
      ),
      body: _pages[_selectedPageIndex]['page'] as Widget,
      bottomNavigationBar: BottomNavigationBar(
        onTap: _selectPage,
        selectedItemColor: Theme.of(context).accentColor,
        unselectedItemColor: Colors.black,
        selectedFontSize: 12,
        unselectedFontSize: 12,
        currentIndex: _selectedPageIndex,
        type: BottomNavigationBarType.fixed,
        items: [
          BottomNavigationBarItem(
            icon: Icon(Icons.home_outlined),
            label: '홈',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.chat_bubble_outline_rounded),
            label: '채팅',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person_outline_sharp),
            label: '마이페이지',
          ),
        ],
      ),
      floatingActionButton:
          _getFAB(_pages[_selectedPageIndex]['isFloating'] as bool),
    );
  }
}

Widget? _getFAB(bool isFloating) {
  if (isFloating) {
    return FloatingActionButton(
      child: Icon(Icons.add),
      foregroundColor: Colors.white,
      onPressed: () {},
    );
  }
}

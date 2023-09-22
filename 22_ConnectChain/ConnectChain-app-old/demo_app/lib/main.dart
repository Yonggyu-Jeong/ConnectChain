import 'package:cloud_firestore/cloud_firestore.dart';
import 'providers/goods/goods_list.dart';
import 'providers/profile/profile_provider.dart';
import 'providers/repositories/profile_repository.dart';
import 'screens/history_screen.dart';
import 'screens/my_page/buy_list_screen.dart';
import 'screens/my_page/watch_list_screen.dart';
import 'screens/product_detail_screen.dart';
import 'screens/product_register_screen.dart';
import 'screens/sign/splash_page.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart' as fbAuth;
import 'package:provider/provider.dart';

import 'screens/tabs_screen.dart';
import 'providers/auth/auth_provider.dart';
import 'providers/repositories/auth_repository.dart';
import 'providers/signin/signin_provider.dart';
import 'providers/signup/signup_provider.dart';
import 'screens/my_page/sell_list_screen.dart';
import 'screens/sign/signin_page.dart';
import 'screens/sign/signup_page.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(DemoApp());
}

class DemoApp extends StatefulWidget {
  @override
  State<DemoApp> createState() => _DemoAppState();
}

class _DemoAppState extends State<DemoApp> {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        Provider<AuthRepository>(
          create: (context) => AuthRepository(
            firebaseFirestore: FirebaseFirestore.instance,
            firebaseAuth: fbAuth.FirebaseAuth.instance,
          ),
        ),
        Provider<ProfileRepository>(
          create: (context) => ProfileRepository(
            firebaseFirestore: FirebaseFirestore.instance,
          ),
        ),
        StreamProvider<fbAuth.User?>(
          create: (context) => context.read<AuthRepository>().user,
          initialData: null,
        ),
        ChangeNotifierProxyProvider<fbAuth.User?, AuthProvider>(
          create: (context) => AuthProvider(
            authRepository: context.read<AuthRepository>(),
          ),
          update: (BuildContext context, fbAuth.User? userStream,
                  AuthProvider? authProvider) =>
              authProvider!..update(userStream),
        ),
        ChangeNotifierProvider<SigninProvider>(
          create: (context) => SigninProvider(
            authRepository: context.read<AuthRepository>(),
          ),
        ),
        ChangeNotifierProvider<SignupProvider>(
          create: (context) => SignupProvider(
            authRepository: context.read<AuthRepository>(),
          ),
        ),
        ChangeNotifierProvider<ProfileProvider>(
          create: (context) => ProfileProvider(
            profileRepository: context.read<ProfileRepository>(),
          ),
        ),
        ChangeNotifierProvider<GoodsList>(
          create: (context) => GoodsList(),
        ),
      ],
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Carrot Market Clone',
        home: SplashPage(),
        routes: {
          SignupPage.routeName: (context) => SignupPage(),
          SigninPage.routeName: (context) => SigninPage(),
          TabsScreen.routeName: (context) => TabsScreen(),
          SellListScreen.routeName: (ctx) => SellListScreen(),
          BuyListScreen.routeName: (ctx) => BuyListScreen(),
          WatchListScreen.routeName: (ctx) => WatchListScreen(),
          ProductDetailScreen.routeName: (ctx) => ProductDetailScreen(),
          ProductRegisterScreen.routeName: (ctx) => ProductRegisterScreen(),
          HistoryScreen.routename: (ctx) => HistoryScreen(),
        },
        theme: ThemeData(
          // primaryColor: Color.fromRGBO(155, 89, 182, 1),
          primaryColor: Color(0xffd96b5d),
          focusColor: Color(0xffCA4E79),
          secondaryHeaderColor: Color(0xffd96b5d),
          dividerColor: Color(0xff7A4069),
        ),
      ),
    );
  }
}

import 'package:flutter/material.dart';

class HomeItem {
  final String imagePath;
  final String title;
  final String location;
  final String time;
  final String price;
  final int likes;

  const HomeItem({
    required this.imagePath,
    required this.title,
    required this.location,
    required this.time,
    required this.price,
    required this.likes,
  });
}

import 'package:flutter/material.dart';

class ChatItem {
  final String profileImagePath;
  final String name;
  final String location;
  final String time;
  final String itemImagePath;
  final String messageLatest;

  const ChatItem({
    required this.profileImagePath,
    required this.name,
    required this.location,
    required this.time,
    required this.itemImagePath,
    required this.messageLatest,
  });
}

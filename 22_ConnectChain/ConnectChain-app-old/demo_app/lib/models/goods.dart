import 'package:freezed_annotation/freezed_annotation.dart';

part 'goods.freezed.dart';
part 'goods.g.dart';

@freezed
class Goods with _$Goods {
  factory Goods({
    required String goods_id,
    required List<String> imagePath,
    required String time,
    required String title,
    required String desc, //설명
    required String price,
    required int likes,
    required bool like, //관심 여부
    required String category,
    required int item_status, //판매중, 거래완료
    required String user_id,
  }) = _Goods;

  factory Goods.fromJson(Map<String, dynamic> json) => _$GoodsFromJson(json);
}

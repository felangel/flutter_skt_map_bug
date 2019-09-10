part of skt_maps_flutter;

class SKTMap extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
        return AndroidView(
          viewType: 'SKTMap',
          onPlatformViewCreated: (_) {},
          creationParams: {},
          creationParamsCodec: const StandardMessageCodec(),
        );
      default:
        return new Text(
            '$defaultTargetPlatform is not yet supported by this plugin');
    }
  }
}

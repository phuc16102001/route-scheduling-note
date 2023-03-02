import {
  LatLng,
  latLngBounds,
  LatLngExpression,
  LeafletEventHandlerFnMap,
  Marker as LeafletMarker
} from "leaflet";
import { useEffect, useMemo, useRef } from "react";
import { Marker, TileLayer, useMap } from "react-leaflet";
import "./index.css";

interface CustomeMapInterface {
  listMarker?: LatLng[];
  listSegment?: LatLngExpression[];
  setSingleMarker: (marker: LatLng) => void;
}

const CustomMap = (props: CustomeMapInterface) => {
  const listMarker = props.listMarker;
  const setSingleMarker = props.setSingleMarker;
  const draggableMarker = useRef<LeafletMarker>(null);
  const map = useMap();

  const markerHandler = useMemo(() => {
    const handler: LeafletEventHandlerFnMap = {
      dragend() {
        const marker = draggableMarker.current;
        if (marker != null) {
          setSingleMarker(marker.getLatLng());
        }
      },
    };
    return handler;
  }, []);

  useEffect(() => {
    if (listMarker) {
      map.flyToBounds(latLngBounds(listMarker));
    }
  }, [listMarker, map]);

  return (
    <>
      <TileLayer url="https://tile.openstreetmap.org/{z}/{x}/{y}.png" />
      {listMarker ? (
        listMarker.length === 1 ? (
          <Marker
            eventHandlers={markerHandler}
            draggable={true}
            ref={draggableMarker}
            position={listMarker[0]}
          />
        ) : (
          listMarker.map((element) => <Marker position={element} />)
        )
      ) : null}
    </>
  );
};

export default CustomMap;

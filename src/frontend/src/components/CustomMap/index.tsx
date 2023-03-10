import {
  LatLng,
  latLngBounds,
  LatLngExpression,
  LeafletEventHandlerFnMap,
  Marker as LeafletMarker,
} from "leaflet";
import { useEffect, useMemo, useRef } from "react";
import { Marker, Polyline, TileLayer, useMap } from "react-leaflet";
import "./index.css";

interface CustomeMapInterface {
  listMarker?: LatLng[];
  listLinePoint?: LatLng[];
  setSingleMarker: (marker: LatLng) => void;
  draggable: boolean;
}

const CustomMap = (props: CustomeMapInterface) => {
  const listMarker = props.listMarker;
  const setSingleMarker = props.setSingleMarker;
  const listLinePoint = props.listLinePoint;
  const draggable = props.draggable;
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
  }, [setSingleMarker]);

  useEffect(() => {
    if (listMarker && listMarker.length > 0) {
      map.flyToBounds(latLngBounds(listMarker));
    }
  }, [listMarker, map]);

  return (
    <>
      <TileLayer url="https://tile.openstreetmap.org/{z}/{x}/{y}.png" />
      {listMarker ? (
        listMarker.length === 1 ? (
          <Marker
            key={"marker"}
            eventHandlers={markerHandler}
            draggable={draggable}
            ref={draggableMarker}
            position={listMarker[0]}
          />
        ) : (
          listMarker.map((element: LatLng, index: number) => (
            <Marker key={`marker_${index}`} position={element} />
          ))
        )
      ) : null}
      {listLinePoint ? <Polyline positions={listLinePoint} /> : null}
    </>
  );
};

export default CustomMap;

import { useParams } from "react-router-dom";

export default function DocumentPage() {
  const { docu_id } = useParams();

  return <div>DocumentPage {docu_id} </div>;
}

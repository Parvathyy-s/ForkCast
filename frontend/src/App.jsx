import { useState } from "react";

import Navbar from "./components/Navbar";
import Hero from "./components/Hero";
import RecommendationForm from "./components/RecommendationForm";
import RecommendationCard from "./components/RecommendationCard";
import Footer from "./components/Footer";

import api from "./services/api";

function App() {

  const [recommendation, setRecommendation] = useState(null);
  const [loading, setLoading] = useState(false);

 async function generateRecommendation(formData) {

  console.log("Sending to backend:", formData);

  try {

    setLoading(true);

    const response = await api.post(
      "/api/recommend",
      formData
    );

    setRecommendation(response.data);

  } catch (error) {

    console.log(error.response);

    alert("Unable to generate recommendation.");

  } finally {

    setLoading(false);

  }

}

  return (
    <div className="bg-slate-50">

      <Navbar />

      <Hero />

      <RecommendationForm
        onSubmit={generateRecommendation}
        loading={loading}
      />

      <RecommendationCard
        recommendation={recommendation}
      />

      <Footer />

    </div>
  );
}

export default App;
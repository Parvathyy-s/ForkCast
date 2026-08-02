import { useState } from "react";

function RecommendationForm({ onSubmit, loading }) {

  const [formData, setFormData] = useState({
    dietType: "",
    mealType: "",
    maxCalories: "",
    maxPrepTime: "",
    cuisine: "",
    healthGoal: "",
    allergy: "",
    preferences: "",
  });

  function handleChange(e) {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  }

  function submit(e) {
    e.preventDefault();

    if (!formData.dietType) {
      alert("Please select a Diet Type.");
      return;
    }

    if (!formData.mealType) {
      alert("Please select a Meal Type.");
      return;
    }

    const request = {};

    Object.entries(formData).forEach(([key, value]) => {

    if (value === "") return;

    // Don't send allergy if user selected "None"
    if (key === "allergy" && value === "none") return;

    if (
        key === "maxCalories" ||
        key === "maxPrepTime"
    ) {
        request[key] = Number(value);
    } else {
        request[key] = value;
    }
 });

    console.log("Sending:", request);

    onSubmit(request);
  }

  return (
    <section
      id="recommend"
      className="max-w-6xl mx-auto py-24 px-8"
    >
      <div className="bg-white rounded-3xl shadow-xl p-12">

        <h2 className="text-4xl font-bold text-slate-800 mb-3">
          Find Your Perfect Meal
        </h2>

        <p className="text-slate-500 mb-10">
          Tell us your preferences and let Gemini AI recommend your perfect meal.
        </p>

        <form
          onSubmit={submit}
          className="grid md:grid-cols-2 gap-6"
        >

          <select
            name="dietType"
            value={formData.dietType}
            onChange={handleChange}
            className="border rounded-xl p-4"
          >
            <option value="">Select Diet Type</option>
            <option value="VEGETARIAN">Vegetarian</option>
            <option value="VEGAN">Vegan</option>
            <option value="NON_VEGETARIAN">Non Vegetarian</option>
          </select>

          <select
            name="mealType"
            value={formData.mealType}
            onChange={handleChange}
            className="border rounded-xl p-4"
          >
            <option value="">Any Meal</option>
            <option value="BREAKFAST">Breakfast</option>
            <option value="LUNCH">Lunch</option>
            <option value="DINNER">Dinner</option>
            <option value="SNACK">Snack</option>
          </select>

          <input
            type="number"
            name="maxCalories"
            placeholder="Maximum Calories"
            value={formData.maxCalories}
            onChange={handleChange}
            className="border rounded-xl p-4"
          />

          <input
            type="number"
            name="maxPrepTime"
            placeholder="Preparation Time (minutes)"
            value={formData.maxPrepTime}
            onChange={handleChange}
            className="border rounded-xl p-4"
          />

          <select
            name="cuisine"
            value={formData.cuisine}
            onChange={handleChange}
            className="border rounded-xl p-4"
            >
            <option value="">Any Cuisine</option>
            <option value="Indian">Indian</option>
            <option value="Italian">Italian</option>
            <option value="Chinese">Chinese</option>
            <option value="Japanese">Japanese</option>
            <option value="Korean">Korean</option>
            <option value="Thai">Thai</option>
            <option value="Mexican">Mexican</option>
            <option value="Mediterranean">Mediterranean</option>
            <option value="American">American</option>
        </select>

        <select
            name="healthGoal"
            value={formData.healthGoal}
            onChange={handleChange}
            className="border rounded-xl p-4"
            >
            <option value="">Select Health Goal</option>
            <option value="Balanced Diet">Balanced Diet</option>
            <option value="Weight Loss">Weight Loss</option>
            <option value="Muscle Gain">Muscle Gain</option>
            <option value="High Protein">High Protein</option>
            <option value="High Fiber">High Fiber</option>
            <option value="Low Carb">Low Carb</option>
            <option value="Heart Healthy">Heart Healthy</option>
            <option value="Diabetic Friendly">Diabetic Friendly</option>
            <option value="PCOS Friendly">PCOS Friendly</option>
         </select>

        <select
            name="allergy"
            value={formData.allergy}
            onChange={handleChange}
            className="border rounded-xl p-4"
            >
            <option value="">Select Allergy</option>
            <option value="none">None</option>
            <option value="milk">Milk</option>
            <option value="gluten">Gluten</option>
            <option value="soy">Soy</option>
            <option value="egg">Egg</option>
            <option value="peanut">Peanut</option>
            <option value="shellfish">Shellfish</option>
        </select>

          <input
            type="text"
            name="preferences"
            placeholder="Additional Preferences"
            value={formData.preferences}
            onChange={handleChange}
            className="border rounded-xl p-4"
          />

          <div className="md:col-span-2">
            <button
              type="submit"
              disabled={loading}
              className="w-full bg-emerald-600 hover:bg-emerald-700 text-white py-4 rounded-xl text-lg font-semibold transition disabled:opacity-60"
            >
              {loading
                ? "🧠 Generating Recommendation..."
                : "🍽 Generate Recommendation"}
            </button>
          </div>

        </form>

      </div>
    </section>
  );
}

export default RecommendationForm;
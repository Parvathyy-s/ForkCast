function RecommendationCard({ recommendation }) {

  if (!recommendation) return null;

  if (!recommendation.recommendedRecipe) {
    return (
      <section className="max-w-6xl mx-auto px-8 pb-20">
        <div className="bg-white rounded-3xl shadow-xl p-12 text-center">

          <h2 className="text-3xl font-bold text-red-500">
            No Recipe Found
          </h2>

          <p className="text-slate-600 mt-4">
            {recommendation.reason}
          </p>

        </div>
      </section>
    );
  }

  const recipe = recommendation.recommendedRecipe;

  return (
    <section className="max-w-6xl mx-auto px-8 pb-20">

      <div className="bg-white rounded-3xl shadow-xl overflow-hidden">

        <div className="grid md:grid-cols-2">

          <img
                src={`/images/${recipe.imageUrl}`}
                alt={recipe.name}
                className="w-full h-full object-cover"
                onError={(e) => {
                    e.target.src = "/images/default.jpg";
                }}
            />

          <div className="p-10">

            <h2 className="text-4xl font-bold">
              {recipe.name}
            </h2>

            <p className="text-slate-500 mt-4">
              {recipe.description}
            </p>

            <div className="grid grid-cols-2 gap-4 mt-8">

              <div className="bg-slate-100 rounded-xl p-4">
                <p>Calories</p>
                <h3>{recipe.calories}</h3>
              </div>

              <div className="bg-slate-100 rounded-xl p-4">
                <p>Protein</p>
                <h3>{recipe.protein} g</h3>
              </div>

              <div className="bg-slate-100 rounded-xl p-4">
                <p>Prep Time</p>
                <h3>{recipe.prepTime} mins</h3>
              </div>

              <div className="bg-slate-100 rounded-xl p-4">
                <p>Cuisine</p>
                <h3>{recipe.cuisine}</h3>
              </div>

            </div>

            <div className="mt-10">

              <h3>🤖 Why this recipe?</h3>

              <p className="whitespace-pre-line">
                {recommendation.reason}
              </p>

            </div>

            <div className="mt-10">

              <h3>💡 AI Tips</h3>

              <p className="whitespace-pre-line">
                {recommendation.aiTips}
              </p>

            </div>

          </div>

        </div>

      </div>

    </section>
  );
}

export default RecommendationCard;
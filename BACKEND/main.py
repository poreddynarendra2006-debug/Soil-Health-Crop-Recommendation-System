import pandas as pd
import pickle
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score

# Load dataset
data = pd.read_csv("Crop_recommendation.csv")

# Separate features and label
X = data.drop("label", axis=1)
y = data["label"]

# Split dataset into training and testing
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

# Train Random Forest model
model = RandomForestClassifier()
model.fit(X_train, y_train)

# Test accuracy
pred = model.predict(X_test)
accuracy = accuracy_score(y_test, pred)

print("Model Accuracy:", accuracy)

# Sample input for prediction
sample = pd.DataFrame(
    [[90, 42, 43, 20, 82, 6.5, 200]],
    columns=["N", "P", "K", "temperature", "humidity", "ph", "rainfall"]
)

# Predict crop
prediction = model.predict(sample)

print("Recommended Crop:", prediction[0])

# Save trained model
pickle.dump(model, open("crop_model.pkl", "wb"))

print("Model saved as crop_model.pkl")